package com.waypoints.webservice;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.waypoints.entity.Usuario;
import com.waypoints.exception.BusinessException;
import com.waypoints.model.UsuarioFA;

@Path("usuario")
public class UsuarioController {

	@Inject
	UsuarioFA usuarioFA = new UsuarioFA();

	/**
	 * @param usuario - usuario a ser autenticado
	 * NOT_FOUND - usuário não encontrado
	 * FORBIDDEN - parâmetros inaceitáveis
	 * @return OK - usuario autenticado com sucesso
	 */
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Usuario usuario) {
		try {
			if ((usuario = usuarioFA.autentica(usuario)) != null) {
				return Response.status(Status.OK).entity(usuario).build();
			}
			return Response.status(Status.NOT_FOUND).entity(usuario).build();
		} catch (BusinessException be) {
			return Response.status(Status.FORBIDDEN).entity(be.getMessage()).build();
		} catch (SQLException sqle) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
		}
	}
	
	@POST
	@Path("cadastro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastro(Usuario usuario) {
		try {
			if ((usuario = usuarioFA.cadastro(usuario)) != null) {
				return Response.status(Status.CREATED).entity(usuario).build();
			}
			return Response.status(Status.BAD_REQUEST).entity(usuario).build();
		} catch (BusinessException be) {
			return Response.status(Status.FORBIDDEN).entity(be.getMessage()).build();
		} catch (SQLException sqle) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
		}
	}
	
	@POST
	@Path("alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterarCadastro(Usuario authUser, Usuario usuario) {
		try {
			// arrumar autenticacao de usuario ao alterar
			if ((usuarioFA.autentica(authUser) != null) && ((usuario = usuarioFA.alterar(usuario)) != null)) {
				return Response.status(Status.OK).entity(usuario).build();
			}
			return Response.status(Status.BAD_REQUEST).entity(usuario).build();
		} catch (BusinessException be) {
			return Response.status(Status.NOT_ACCEPTABLE).entity(be.getMessage()).build();
		} catch (SQLException sqle) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
		}
	}
	
	@POST
	@Path("excluir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluirUsuario(Usuario usuario) {
		try {
			if ((usuarioFA.autentica(usuario) != null) && (usuarioFA.excluir(usuario) == null)) {
				return Response.status(Status.OK).entity(usuario).build();
			}
			return Response.status(Status.NOT_FOUND).entity(usuario).build();
		} catch (BusinessException be) {
			return Response.status(Status.FORBIDDEN).entity(be.getMessage()).build();
		} catch (SQLException sqle) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
		}
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") Integer id) {
		try {
			Usuario usuario = usuarioFA.getById(id);
			if (usuario != null) {
				return Response.status(Status.OK).entity(usuario).build();
			}
			return Response.status(Status.BAD_REQUEST).entity(usuario).build();
		} catch (BusinessException be) {
			return Response.status(Status.NOT_ACCEPTABLE).entity(be.getMessage()).build();
		} catch (SQLException sqle) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
		}
	}
	
	@POST
	@Path("teste")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluirUsuario(ArrayList<Usuario> users) {
		for (Usuario u : users) {
			System.out.println(u.getNome());
		}
		return Response.status(Status.OK).build();
	}
}