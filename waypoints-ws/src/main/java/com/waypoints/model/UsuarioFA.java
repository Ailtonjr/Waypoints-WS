package com.waypoints.model;

import java.sql.SQLException;

import javax.inject.Inject;

import com.waypoints.dao.UsuarioDAO;
import com.waypoints.entity.Usuario;
import com.waypoints.exception.BusinessException;
import com.waypoints.util.EmailUtil;

public class UsuarioFA {

	private final int TAM_MIN_SENHA = 8; 
	
	@Inject
	UsuarioDAO usuarioDAO = new UsuarioDAO();

	public Usuario login(Usuario usuario) throws BusinessException, SQLException {
		if ((usuario.getEmail() == null)
				|| (usuario.getEmail().isEmpty())
				|| (!EmailUtil.isValid(usuario.getEmail()))) {
			throw new BusinessException("O email informado é inválido.");
		}
		if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
			throw new BusinessException("A senha informada é inválida.");
		}
		if (usuarioDAO.getByEmail(usuario.getEmail()) == null) {
			throw new BusinessException("E-mail não cadastrado."); 
		}
		
		return usuarioDAO.doLogin(usuario);
	}

	public Usuario cadastro(Usuario usuario) throws BusinessException, SQLException {
		if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
			throw new BusinessException("O nome informado é inválido.");
		}
		if ((usuario.getEmail() == null)
				|| (usuario.getEmail().isEmpty())
				|| (!EmailUtil.isValid(usuario.getEmail()))) {
			throw new BusinessException("O email informado é inválido.");
		}
		if ((usuario.getSenha() == null)
				|| (usuario.getSenha().isEmpty())) {
			throw new BusinessException("A senha informada é inválida.");
		}
		if (usuario.getSenha().length() < TAM_MIN_SENHA) {
			throw new BusinessException("A senha deve ter pelo menos \"" + TAM_MIN_SENHA + "\" caracteres.");
		}
		
		if (usuarioDAO.getByEmail(usuario.getEmail()) != null) {
			throw new BusinessException("Este e-mail já está cadastrado."); 
		}
		if (usuario.getSexo() == null) {
			throw new BusinessException("O sexo deve ser selecionado.");
		}
		return usuarioDAO.cadastrar(usuario);
	}
	
	public Usuario getById(Integer id) throws BusinessException, SQLException {
		if ((id == null) || (id < 0)) {
			throw new BusinessException("ID informado inválido.");
		}
		return usuarioDAO.findById(id);
	}
	
}
