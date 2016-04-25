//package com.waypoints.webservice;
//
//import java.sql.SQLException;
//
//import javax.inject.Inject;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
//
//import com.waypoints.entity.Grupo;
//import com.waypoints.entity.Usuario;
//import com.waypoints.exception.BusinessException;
//import com.waypoints.model.GrupoFA;
//import com.waypoints.model.UsuarioFA;
//
//@Path("grupo")
//public class GrupoController {
//
//	@Inject
//	GrupoFA grupoFA = new GrupoFA();
//	@Inject
//	UsuarioFA usuarioFA = new UsuarioFA();
//	
//	
//	@POST
//	@Path("cadastro")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response cadastrarGrupo(Usuario authUser, Grupo grupo) {
//		try {
//			if ((usuarioFA.autentica(authUser) != null) && ((grupo = grupoFA.cadastro(grupo)) != null)) {
//				return Response.ok().status(Status.CREATED).entity(grupo).build();
//			}
//			return Response.status(Status.BAD_REQUEST).entity(grupo).build();
//		} catch (BusinessException be) {
//			return Response.status(Status.NOT_ACCEPTABLE).entity(be.getMessage()).build();
//		} catch (SQLException sqle) {
//			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
//		}
//	}
//	
//	@POST
//	@Path("cadastro")
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response cadastrarGrupo(@FormDataParam("authUser") Usuario authUser, @FormDataParam("grupo") Grupo grupo) {
//		try {
//			if ((usuarioFA.autentica(authUser) != null) && ((grupo = grupoFA.cadastro(grupo)) != null)) {
//				return Response.ok().status(Status.CREATED).entity(grupo).build();
//			}
//			return Response.status(Status.BAD_REQUEST).entity(grupo).build();
//		} catch (BusinessException be) {
//			return Response.status(Status.NOT_ACCEPTABLE).entity(be.getMessage()).build();
//		} catch (SQLException sqle) {
//			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
//		}
//	}
//	
//	
//	@POST
//	@Path("alterar")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response alterarGrupo(Usuario authUser, Grupo grupo) {
//		try {
//			if ((usuarioFA.autentica(authUser) != null) && (grupoFA.cadastro(grupo) != null)) {
//				return Response.ok().status(Status.OK).entity(grupo).build();
//			}
//			return Response.status(Status.BAD_REQUEST).entity(grupo).build();
//		} catch (BusinessException be) {
//			return Response.status(Status.NOT_ACCEPTABLE).entity(be.getMessage()).build();
//		} catch (SQLException sqle) {
//			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
//		}
//	}
//	
//	@POST
//	@Path("excluir")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response excluirGrupo(Usuario authUser, Grupo grupo) {
//		try {
//			if ((usuarioFA.autentica(authUser) != null) && (((grupo = grupoFA.excluir(grupo)) == null))) {
//				return Response.ok().status(Status.OK).entity(grupo).build();
//			}
//			return Response.status(Status.BAD_REQUEST).entity(grupo).build();
//		} catch (BusinessException be) {
//			return Response.status(Status.NOT_ACCEPTABLE).entity(be.getMessage()).build();
//		} catch (SQLException sqle) {
//			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
//		}
//	}
//	
//	@GET
//	@Path("{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response find(@PathParam("id") Integer id) {
//		try {
//			Usuario usuario = grupoFA.getById(id);
//			if (usuario != null) {
//				return Response.ok().entity(usuario).build();
//			}
//			return Response.status(Status.BAD_REQUEST).entity(usuario).build();
//		} catch (BusinessException be) {
//			return Response.status(Status.NOT_ACCEPTABLE).entity(be.getMessage()).build();
//		} catch (SQLException sqle) {
//			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
//		}
//	}
//	
//}