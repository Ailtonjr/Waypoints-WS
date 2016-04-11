package com.waypoints.webservice;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
	 * 
	 * @param usuario
	 * @return usuario se login for efetuado com sucesso
	 * @return Status.CREATED - sucesso na criação do usuário
	 * @return Status.NOT_ACCEPTABLE - parâmetros inaceitáveis
	 */
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Usuario usuario) {
		if (usuario != null) {
			try {
				usuario = usuarioFA.login(usuario);
				return Response.ok().entity(usuario).build();
			} catch (BusinessException be) {
				return Response.status(Status.NOT_ACCEPTABLE).entity(be.getMessage()).build();
			} catch (SQLException sqle) {
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
			}
		} else {
			return Response.status(Status.NO_CONTENT).entity("O valor do parâmetro de entrada é nulo").build();
		}
	}

	
	@POST
	@Path("cadastro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastro(Usuario usuario) {
		if (usuario != null) {
			try {
				usuario = usuarioFA.cadastro(usuario);
				return Response.ok().status(Status.CREATED).entity(usuario).build();
			} catch (BusinessException be) {
				return Response.status(Status.NOT_ACCEPTABLE).entity(be.getMessage()).build();
			} catch (SQLException sqle) {
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
			}
		} else {
			return Response.status(Status.NO_CONTENT).entity("O valor do parâmetro de entrada é nulo").build();
		}
	}
	
}
