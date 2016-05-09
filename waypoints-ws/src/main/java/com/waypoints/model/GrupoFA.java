package com.waypoints.model;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.inject.Inject;

import com.waypoints.dao.GrupoDAO;
import com.waypoints.dao.UsuarioDAO;
import com.waypoints.entity.Grupo;
import com.waypoints.entity.Integrante;
import com.waypoints.exception.BusinessException;

public class GrupoFA {

	@Inject
	GrupoDAO grupoDAO = new GrupoDAO();
	@Inject
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	
	public Grupo cadastro(Grupo grupo) throws BusinessException, SQLException {
		try {
			validaGrupo(grupo);
		} catch (BusinessException be) {
			throw new BusinessException(be.getMessage());
		}
		return grupoDAO.cadastrar(grupo);
	}
	
	public Grupo alterar(Grupo grupo) throws BusinessException, SQLException {
		try {
			validaAlterarGrupo(grupo);
		} catch (BusinessException be) {
			throw new BusinessException(be.getMessage());
		}
		return grupoDAO.alterar(grupo);
	}
	
	public Grupo excluir(Grupo grupo) throws BusinessException, SQLException {
		return grupoDAO.excluir(grupo);
	}
	
	public Grupo getById(Integer id) throws BusinessException, SQLException {
		if ((id == null) || (id < 0)) {
			throw new BusinessException("ID informado inválido.");
		}
		return grupoDAO.findById(id);
	}
	
	public ArrayList<Grupo> getByProprietarioId(Long id) throws BusinessException, SQLException {
		if ((id == null) || (id < 0)) {
			throw new BusinessException("ID informado inválido.");
		}
		return grupoDAO.findByProprietarioId(id);
	}
	
	private void validaGrupo(Grupo grupo) throws BusinessException, SQLException {
		if (grupo.getNome() == null || grupo.getNome().isEmpty()) {
			throw new BusinessException("O nome informado é inválido.");
		}
		if (grupoDAO.getByNome(grupo.getNome()) != null) {
			throw new BusinessException("Este grupo já está cadastrado.");
		}
		for (Integrante i : grupo.getIntegrantes()) {
			if (usuarioDAO.getByEmail(i.getUsuario().getEmail()) == null) {
				throw new BusinessException("Integrante não cadastrado."); 
			}
		}
	}
	
	private void validaAlterarGrupo(Grupo grupo) throws BusinessException, SQLException {
		if (grupo.getNome() == null || grupo.getNome().isEmpty()) {
			throw new BusinessException("O nome informado é inválido.");
		}
		for (Integrante i : grupo.getIntegrantes()) {
			if (usuarioDAO.getByEmail(i.getUsuario().getEmail()) == null) {
				throw new BusinessException("Integrante não cadastrado."); 
			}
		}
	}
	
}
