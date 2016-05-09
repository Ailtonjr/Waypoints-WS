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

import com.waypoints.entity.Grupo;
import com.waypoints.exception.BusinessException;
import com.waypoints.model.GrupoFA;

@Path("grupo")
public class GrupoController {

	@Inject
	GrupoFA grupoFA = new GrupoFA();

	/**
	 * @param grupo - usuario a ser autenticado
	 * NOT_FOUND - usuário não encontrado
	 * FORBIDDEN - parâmetros inaceitáveis
	 * @return OK - usuario autenticado com sucesso
	 */
	@POST
	@Path("cadastro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastro(Grupo grupo) {
		try {
			if ((grupo = grupoFA.cadastro(grupo)) != null) {
				return Response.status(Status.CREATED).entity(grupo).build();
			}
			return Response.status(Status.BAD_REQUEST).entity(grupo).build();
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
	public Response alterarCadastro(Grupo grupo) {
		try {
			if ((grupo = grupoFA.alterar(grupo)) != null) {
				return Response.status(Status.OK).entity(grupo).build();
			}
			return Response.status(Status.NOT_FOUND).entity(grupo).build();
		} catch (BusinessException be) {
			return Response.status(Status.FORBIDDEN).entity(be.getMessage()).build();
		} catch (SQLException sqle) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
		}
	}
	
	@POST
	@Path("excluir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluirGrupo(Grupo grupo) {
		try {
			if (grupoFA.excluir(grupo) == null) {
				return Response.status(Status.OK).entity(grupo).build();
			}
			return Response.status(Status.NOT_FOUND).entity(grupo).build();
		} catch (BusinessException be) {
			return Response.status(Status.FORBIDDEN).entity(be.getMessage()).build();
		} catch (SQLException sqle) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
		}
	}
	
	@GET
	@Path("proprietario/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") Long proprietarioId) {
		try {
			ArrayList<Grupo> grupos;
			if ((grupos = grupoFA.getByProprietarioId(proprietarioId)) != null) {
				return Response.status(Status.OK).entity(grupos).build();
			}
			return Response.status(Status.NOT_FOUND).entity(proprietarioId).build();
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
			Grupo grupo;
			if ((grupo = grupoFA.getById(id)) != null) {
				return Response.status(Status.OK).entity(grupo).build();
			}
			return Response.status(Status.NOT_FOUND).entity(grupo).build();
		} catch (BusinessException be) {
			return Response.status(Status.FORBIDDEN).entity(be.getMessage()).build();
		} catch (SQLException sqle) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
		}
	}
	
}