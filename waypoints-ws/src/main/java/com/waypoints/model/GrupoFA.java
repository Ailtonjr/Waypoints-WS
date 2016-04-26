package com.waypoints.model;

import java.sql.SQLException;

import javax.inject.Inject;

import com.waypoints.dao.GrupoDAO;
import com.waypoints.entity.Grupo;
import com.waypoints.exception.BusinessException;

public class GrupoFA {

	@Inject
	GrupoDAO grupoDAO = new GrupoDAO();

	public Grupo cadastro(Grupo grupo) throws BusinessException, SQLException {
		try {
			validaGrupo(grupo);
		} catch (BusinessException be) {
			throw new BusinessException(be.getMessage());
		}
		return grupoDAO.cadastrar(grupo);
	}
	
//	public Grupo alterar(Grupo grupo) throws BusinessException, SQLException {
//		try {
//			validaGrupo(grupo);
//		} catch (BusinessException be) {
//			throw new BusinessException(be.getMessage());
//		}
//		return grupoDAO.alterar(grupo);
//	}
//	
//	public Grupo excluir(Grupo grupo) throws BusinessException, SQLException {
//		return grupoDAO.excluir(grupo);
//	}
//	
//	public Grupo getById(Integer id) throws BusinessException, SQLException {
//		if ((id == null) || (id < 0)) {
//			throw new BusinessException("ID informado inválido.");
//		}
//		return grupoDAO.findById(id);
//	}
//	
	private void validaGrupo(Grupo grupo) throws BusinessException, SQLException {
		if (grupo.getNome() == null || grupo.getNome().isEmpty()) {
			throw new BusinessException("O nome informado é inválido.");
		}
//		if ((grupo.getEmail() == null)
//				|| (grupo.getEmail().isEmpty())
//				|| (!EmailUtil.isValid(grupo.getEmail()))) {
//			throw new BusinessException("O email informado é inválido.");
//		}
//		if ((grupo.getSenha() == null)
//				|| (grupo.getSenha().isEmpty())) {
//			throw new BusinessException("A senha informada é inválida.");
//		}
//		if (grupo.getSenha().length() < TAM_MIN_SENHA) {
//			throw new BusinessException("A senha deve ter pelo menos \"" + TAM_MIN_SENHA + "\" caracteres.");
//		}
//		
//		if (usuarioDAO.getByEmail(grupo.getEmail()) != null) {
//			throw new BusinessException("Este e-mail já está cadastrado."); 
//		}
//		if (grupo.getSexo() == null) {
//			throw new BusinessException("O sexo deve ser selecionado.");
//		}
	}
	
}
