package com.waypoints.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.waypoints.entity.Grupo;
import com.waypoints.util.Conexao;

public class GrupoDAO {
	
//	public Usuario getById(Long id) throws SQLException {
//		String SQL = "SELECT * FROM usuario WHERE id=?";
//		Map<String, Object> params = new HashMap<>();
//		params.put("id", id);
//		
//		return getSingleResult(SQL, params);
//	}
//	
//	public Usuario findById(Integer id) throws SQLException {
//		String SQL = "SELECT * FROM usuario WHERE id=?";
//		Map<String, Object> params = new HashMap<>();
//		params.put("id", id);
//		
//		return getSingleResult(SQL, params);
//	}
	
	public Grupo cadastrar(Grupo grupo) throws SQLException {
		String SQL = "INSERT INTO usuario (nome, email, senha, categoria_cnh, sexo) "
				+ "VALUES (?,?,?,?,?);";
		PreparedStatement pStmt = Conexao.getPreparedStatement(SQL);
		try {
//			pStmt.setString(1, grupo.getNome());
//			pStmt.setString(2, grupo.getEmail());
//			pStmt.setString(3, CriptografiaUtil.encrypt(grupo.getSenha()));
//			pStmt.setString(4, grupo.getCategoriaCNH());
//			pStmt.setString(5, grupo.getSexo().toString());
			
			if (pStmt.executeUpdate() > 0) {
				return grupo;
			}

		} catch (SQLException ex) {
			Logger.getLogger(GrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
//	
//	public Usuario alterar(Usuario usuario) throws SQLException {
//		String SQL = "UPDATE usuario "
//				+ "SET nome=?, email=?, senha=?, categoria_cnh=?, sexo=? "
//				+ "WHERE id=?;";
//		PreparedStatement pStmt = Conexao.getPreparedStatement(SQL);
//		try {
//			pStmt.setString(1, usuario.getNome());
//			pStmt.setString(2, usuario.getEmail());
//			pStmt.setString(3, CriptografiaUtil.encrypt(usuario.getSenha()));
//			pStmt.setString(4, usuario.getCategoriaCNH());
//			pStmt.setString(5, usuario.getSexo().toString());
//			pStmt.setLong(6, usuario.getId());
//			
//			if (pStmt.executeUpdate() > 0) {
//				return usuario;
//			}
//
//		} catch (SQLException ex) {
//			Logger.getLogger(GrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
//		}
//		return null;
//	}
//	
//	public Usuario excluir(Usuario usuario) throws SQLException {
//		String SQL = "DELETE FROM usuario WHERE id=?;";
//		PreparedStatement pStmt = Conexao.getPreparedStatement(SQL);
//		try {
//			pStmt.setLong(1, usuario.getId());
//			
//			if (pStmt.executeUpdate() > 0) {
//				return null;
//			}
//
//		} catch (SQLException ex) {
//			Logger.getLogger(GrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
//		}
//		return usuario;
//	}
//	
//	private Usuario getSingleResult(String query, Map<String, Object> filter) throws SQLException {
//		PreparedStatement pStmt = Conexao.getPreparedStatement(query);
//
//		if (filter != null) {
//			// seta os parâmetros
//			int pos = filter.size();
//			for (Entry<String, Object> entry : filter.entrySet()) {
//				pStmt.setObject(pos, entry.getValue());
//				pos--;
//			}
//			
//		}
//
//		ResultSet rs = pStmt.executeQuery();
//		if (rs.next()) {
//			Usuario u = new Usuario();
//			u.setId(rs.getLong("id"));
//			u.setNome(rs.getString("nome"));
//			u.setEmail(rs.getString("email"));
//			u.setCategoriaCNH(rs.getString("categoria_cnh"));
////			u.setDataNascimento(rs.getDate("data_nascimento"));
//			u.setSexo(Sexo.valueOf(rs.getString("sexo")));
//			return u;
//		}
//
//		return null;
//	}
	
}
