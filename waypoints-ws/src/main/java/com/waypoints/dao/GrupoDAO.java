package com.waypoints.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import com.waypoints.entity.Grupo;
import com.waypoints.entity.Integrante;
import com.waypoints.util.Conexao;

public class GrupoDAO {
	
	@Inject
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public Grupo getById(Long id) throws SQLException {
		String SQL = "SELECT * FROM grupo WHERE id=?";
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		
		return getSingleResult(SQL, params);
	}
	
	public Grupo findById(Integer id) throws SQLException {
		String SQL = "SELECT * FROM grupo WHERE id=?";
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		
		return getSingleResult(SQL, params);
	}
	
	public Grupo cadastrar(Grupo grupo) throws SQLException {
		String SQL = "INSERT INTO grupo (nome, proprietario, ramo) "
				+ "VALUES (?,?,?);";

		PreparedStatement pStmt = Conexao.getPreparedStatement(SQL);
		pStmt.setString(1, grupo.getNome());
		pStmt.setString(2, grupo.getNomeProprietario());
		pStmt.setString(3, grupo.getRamo());
		
		if (pStmt.executeUpdate() > 0) {
			grupo.setId(getByNome(grupo.getNome()).getId());
			addIntegrantes(grupo);
			return grupo;
		}
		return null;
	}
	
	public Grupo addIntegrantes(Grupo grupo) throws SQLException {
		for (Integrante i : grupo.getIntegrantes()) {
			if (!isUsuarioEmGrupo(i.getUsuario().getId(), grupo.getId())) {
				String SQL = "INSERT INTO grupo_usuario (id_grupo, id_usuario, papel) "
						+ "VALUES (?,?,?);";

				PreparedStatement pStmt = Conexao.getPreparedStatement(SQL);
				pStmt.setLong(1, grupo.getId());
				pStmt.setLong(2, i.getUsuario().getId());
				pStmt.setString(3, i.getPapel());
					
				pStmt.executeUpdate();
			}
		}
		return grupo;
	}
	
	public Grupo alterar(Grupo grupo) throws SQLException {
		String SQL = "UPDATE grupo "
				+ "SET nome=?, proprietario=?, ramo=? "
				+ "WHERE id=?;";
		
		PreparedStatement pStmt = Conexao.getPreparedStatement(SQL);
		pStmt.setString(1, grupo.getNome());
		pStmt.setString(2, grupo.getNomeProprietario());
		pStmt.setString(3, grupo.getRamo());
		pStmt.setLong(4, grupo.getId());
		
		if (pStmt.executeUpdate() > 0) {
			removeIntegrantes(grupo);
			addIntegrantes(grupo);
			return grupo;
		}
		return null;
	}
	
	private void removeIntegrantes(Grupo grupo) throws SQLException {
		String SQL = "DELETE FROM grupo_usuario "
				+ "WHERE id_grupo=?;";
		PreparedStatement pStmt = Conexao.getPreparedStatement(SQL);
		pStmt.setLong(1, grupo.getId());
		pStmt.executeUpdate();
	}
	
	
	public Grupo excluir(Grupo grupo) throws SQLException {
		String SQL = "DELETE FROM grupo WHERE id=?;";
		
		PreparedStatement pStmt = Conexao.getPreparedStatement(SQL);
		pStmt.setLong(1, grupo.getId());
		
		removeIntegrantes(grupo);
		if (pStmt.executeUpdate() > 0) {
			return null;
		}

		return grupo;
	}
	

	public Grupo getByNome(String nome) throws SQLException {
		String SQL = "SELECT * FROM grupo WHERE nome=?";
		Map<String, Object> params = new HashMap<>();
		params.put("nome", nome);
		
		return getSingleResult(SQL, params);
	}
	
	private Grupo getSingleResult(String query, Map<String, Object> filter) throws SQLException {
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
			Grupo g = new Grupo();
			g.setId(rs.getLong("id"));
			g.setNome(rs.getString("nome"));
			g.setNomeProprietario(rs.getString("proprietario"));
			g.setRamo(rs.getString("ramo"));
			g.setIntegrantes(findIntegrantes(g.getId()));
			return g;
		}

		return null;
	}

	private List<Integrante> findIntegrantes(Long grupoId) throws SQLException {
		String SQL = "SELECT * FROM grupo_usuario WHERE id_grupo=?";
		
		PreparedStatement pStmt = Conexao.getPreparedStatement(SQL);
		pStmt.setLong(1, grupoId);
		
		List<Integrante> integrantes = new ArrayList<>();				
		ResultSet rs = pStmt.executeQuery();
		while (rs.next()) {
			Integrante i = new Integrante();
			i.setUsuario(usuarioDAO.getById(rs.getLong("id")));
			i.setPapel(rs.getString("papel"));
			
			integrantes.add(i);
		}
		return integrantes;
	}
	
	public boolean isUsuarioEmGrupo(Long idUsuario, Long idGrupo) throws SQLException {
		String SQL = "SELECT * FROM grupo_usuario WHERE id_usuario=? AND id_grupo=?";
		
		PreparedStatement pStmt = Conexao.getPreparedStatement(SQL);
		pStmt.setLong(1, idUsuario);
		pStmt.setLong(2, idGrupo);

		ResultSet rs = pStmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
	
}
