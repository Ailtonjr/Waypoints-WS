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

	public Usuario autentica(Usuario usuario) throws BusinessException, SQLException {
		if ((usuario.getEmail() == null)
				|| (usuario.getEmail().isEmpty())
				|| (!EmailUtil.isValid(usuario.getEmail()))) {
			throw new BusinessException("O email informado � inv�lido.");
		}
		if (usuarioDAO.getByEmail(usuario.getEmail()) == null) {
			throw new BusinessException("E-mail n�o cadastrado."); 
		}
		
		return usuarioDAO.doLogin(usuario);
	}

	public Usuario cadastro(Usuario usuario) throws BusinessException, SQLException {
		try {
			validaUsuario(usuario);
		} catch (BusinessException be) {
			throw new BusinessException(be.getMessage());
		}
		return usuarioDAO.cadastrar(usuario);
	}
	
	public Usuario alterar(Usuario usuario) throws BusinessException, SQLException {
		try {
			validaAlteracaoUsuario(usuario);
		} catch (BusinessException be) {
			throw new BusinessException(be.getMessage());
		}
		return usuarioDAO.alterar(usuario);
	}
	
	public Usuario excluir(Usuario usuario) throws BusinessException, SQLException {
		return usuarioDAO.excluir(usuario);
	}
	
	public Usuario getById(Integer id) throws BusinessException, SQLException {
		if ((id == null) || (id < 0)) {
			throw new BusinessException("ID informado inv�lido.");
		}
		return usuarioDAO.findById(id);
	}
	
	public Usuario getByEmail(String email) throws BusinessException, SQLException {
		if ((email == null)
				|| (email.isEmpty())
				|| (!EmailUtil.isValid(email))) {
			throw new BusinessException("Email informado inv�lido.");
		}
		return usuarioDAO.getByEmail(email);
	}
	
	public boolean recuperaSenha(String email) throws BusinessException, SQLException {
		if ((email == null)
				|| (email.isEmpty())
				|| (!EmailUtil.isValid(email))
				|| (usuarioDAO.getByEmail(email) == null)) {
			throw new BusinessException("Email informado inv�lido.");
		}
		
		return usuarioDAO.recuperaSenha(email);
	}
	
	private void validaUsuario(Usuario usuario) throws BusinessException, SQLException {
		if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
			throw new BusinessException("O nome informado � inv�lido.");
		}
		if ((usuario.getEmail() == null)
				|| (usuario.getEmail().isEmpty())
				|| (!EmailUtil.isValid(usuario.getEmail()))) {
			throw new BusinessException("O email informado � inv�lido.");
		}
		if ((usuario.getSenha() == null) || (usuario.getSenha().isEmpty())) {
			throw new BusinessException("A senha informada � inv�lida.");
		}
		if (usuario.getSenha().length() < TAM_MIN_SENHA) {
			throw new BusinessException("A senha deve ter pelo menos \"" + TAM_MIN_SENHA + "\" caracteres.");
		}
		
		if (usuarioDAO.getByEmail(usuario.getEmail()) != null) {
			throw new BusinessException("Este e-mail j� est� cadastrado."); 
		}
		if (usuario.getSexo() == null) {
			throw new BusinessException("O sexo deve ser selecionado.");
		}
	}
	
	private void validaAlteracaoUsuario(Usuario usuario) throws BusinessException, SQLException {
		if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
			throw new BusinessException("O nome informado � inv�lido.");
		}
		if ((usuario.getEmail() == null)
				|| (usuario.getEmail().isEmpty())
				|| (!EmailUtil.isValid(usuario.getEmail()))) {
			throw new BusinessException("O email informado � inv�lido.");
		}
		if ((usuario.getSenha() == null) || (usuario.getSenha().isEmpty())) {
			throw new BusinessException("A senha informada � inv�lida.");
		}
		if (usuario.getSenha().length() < TAM_MIN_SENHA) {
			throw new BusinessException("A senha deve ter pelo menos \"" + TAM_MIN_SENHA + "\" caracteres.");
		}
		if (usuario.getSexo() == null) {
			throw new BusinessException("O sexo deve ser selecionado.");
		}
	}
	
}
