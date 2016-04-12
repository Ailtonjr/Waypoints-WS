package com.waypoints.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.waypoints.entity.Usuario;
import com.waypoints.entity.Usuario.Sexo;
import com.waypoints.util.Conexao;
import com.waypoints.util.CriptografiaUtil;

public class UsuarioDAO {

//	@Deprecated
//	public boolean inserir(Usuario usuario) {
//		String sql = "INSERT INTO usuario(email,senha) VALUES(?,?)";
//		PreparedStatement pst = Conexao.getPreparedStatement(sql);
//		try {
//			pst.setString(1, usuario.getEmail());
//			pst.setString(2, usuario.getSenha());
//
//			return (pst.executeUpdate() > 0);
//
//		} catch (SQLException ex) {
//			Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
//		}
//
//		return false;
//	}
//
//	@Deprecated
//	public boolean atualizar(Usuario usuario) {
//		String sql = "UPDATE usuario SET senha=? WHERE id=?";
//		PreparedStatement pst = Conexao.getPreparedStatement(sql);
//		try {
//
//			pst.setLong(0, usuario.getId());
//			pst.setString(1, usuario.getSenha());
//			pst.setString(4, usuario.getEmail());
//			
//			return (pst.executeUpdate() > 0);
//
//		} catch (SQLException ex) {
//			Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
//		}
//
//		return false;
//	}
//
//	@Deprecated
//	public boolean excluir(Usuario usuario) {
//		String sql = "DELETE FROM usuario WHERE usuario=?";
//		PreparedStatement pst = Conexao.getPreparedStatement(sql);
//		try {
//
//			pst.setLong(0, usuario.getId());
//			
//			return (pst.executeUpdate() > 0);
//
//		} catch (SQLException ex) {
//			Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
//		}
//
//		return false;
//	}
//
//	@Deprecated
//	public List<Usuario> listaUsuarios() {
//		String sql = "SELECT * FROM usuario";
//		List<Usuario> usuarios = new ArrayList<Usuario>();
//
//		PreparedStatement pst = Conexao.getPreparedStatement(sql);
//		try {
//
//			ResultSet res = pst.executeQuery();
//			while (res.next()) {
//				Usuario usuario = new Usuario();
//				
//				usuario.setId(res.getLong("id"));
//				usuario.setNome(res.getString("nome"));
//				usuario.setEmail(res.getString("email"));
//				usuario.setSenha(res.getString("senha"));
//				usuario.setCategoriaCNH(res.getString("categoria_cnh"));
//				usuario.setDataNascimento(res.getDate("data_nascimento"));
//				usuario.setSexo(Sexo.valueOf(res.getString("sexo")));
//				
//				usuarios.add(usuario);
//			}
//
//		} catch (SQLException ex) {
//			Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
//		}
//
//		return usuarios;
//	}

	
	public Usuario getById(Long id) throws SQLException {
		String SQL = "SELECT * FROM usuario WHERE id=?";
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		
		return getSingleResult(SQL, params);
	}
	
	public Usuario getByEmail(String email) throws SQLException {
		String SQL = "SELECT * FROM usuario WHERE email=?";
		Map<String, Object> params = new HashMap<>();
		params.put("email", email);
		
		return getSingleResult(SQL, params);
	}
	
	public boolean findByEmail(String email) throws SQLException {
		String SQL = "SELECT * FROM usuario WHERE email=?";
		Map<String, Object> params = new HashMap<>();
		params.put("email", email);
		
		return (getSingleResult(SQL, params) != null);
	}

	public Usuario doLogin(Usuario usuario) throws SQLException {
		String SQL = "SELECT * FROM usuario WHERE email=? AND senha=?";
		Map<String, Object> params = new HashMap<>();
		params.put("email", usuario.getEmail());
		params.put("senha", CriptografiaUtil.encrypt(usuario.getSenha()));
		
		return getSingleResult(SQL, params);
	}
	
	public Usuario cadastrar(Usuario usuario) throws SQLException {
		String SQL = "INSERT INTO usuario (nome, email, senha, categoria_cnh, sexo) "
				+ "VALUES (?,?,?,?,?);";
		PreparedStatement pStmt = Conexao.getPreparedStatement(SQL);
		try {
			pStmt.setString(1, usuario.getNome());
			pStmt.setString(2, usuario.getEmail());
			pStmt.setString(3, CriptografiaUtil.encrypt(usuario.getSenha()));
			pStmt.setString(4, usuario.getCategoriaCNH());
			pStmt.setString(5, usuario.getSexo().toString());
			
			if (pStmt.executeUpdate() > 0) {
				return usuario;
			}

		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public Usuario findById(Integer id) throws SQLException {
		String SQL = "SELECT * FROM usuario WHERE id=?";
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		
		return getSingleResult(SQL, params);
	}
	
	private Usuario getSingleResult(String query, Map<String, Object> filter) throws SQLException {
		PreparedStatement pStmt = Conexao.getPreparedStatement(query);

		if (filter != null) {
			// seta os parâmetros
			int pos = filter.size();
			for (Entry<String, Object> entry : filter.entrySet()) {
				pStmt.setObject(pos, entry.getValue());
				pos--;
			}
			
		}

		ResultSet rs = pStmt.executeQuery();
		if (rs.next()) {
			Usuario u = new Usuario();
			u.setId(rs.getLong("id"));
			u.setNome(rs.getString("nome"));
			u.setEmail(rs.getString("email"));
			u.setCategoriaCNH(rs.getString("categoria_cnh"));
//			u.setDataNascimento(rs.getDate("data_nascimento"));
			u.setSexo(Sexo.valueOf(rs.getString("sexo")));
			return u;
		}

		return null;
	}
	
}
