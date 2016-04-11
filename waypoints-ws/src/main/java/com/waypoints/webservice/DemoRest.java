package com.waypoints.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.waypoints.entity.Usuario;

@Path("demo")
public class DemoRest {

	@GET
	@Path("hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "aqui vai retornar meu JSON";
	}

	@GET
	@Path("usuarios/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuario(@PathParam("id") Integer id) {
		List<Usuario> usuarios = new ArrayList<>();
		Usuario u1, u2;
		u1 = new Usuario();
		u2 = new Usuario();
		u1.setNome("Jhony");
		u2.setNome("Romulo");
		usuarios.add(u1);
		usuarios.add(u2);
		return Response.ok().entity(usuarios.get(id)).build();
	}

	@GET
	@Path("usuarios")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarios(@Context UriInfo uriInfo) {
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		// Sexo s = Sexo.valueOf(sexo);
		List<Usuario> usuarios = new ArrayList<>();
		Usuario u1, u2, u3;
		u1 = new Usuario();
		u2 = new Usuario();
		u3 = new Usuario();
		u1.setNome("Jhony");
		u2.setNome("Romulo");
		u3.setNome("Romisvaldo");
		usuarios.add(u1);
		usuarios.add(u2);
		usuarios.add(u3);
		return Response.ok().entity(usuarios.stream().filter((batata) -> {
			return batata.getNome().toLowerCase().contains(queryParams.getFirst("nome").toLowerCase());
		}).collect(Collectors.toList())).build();
	}

	@POST
	@Path("usuarios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveUsuario(Usuario usuario) {
		try {
			return Response.ok().status(Status.CREATED).entity(usuario).build();
		} catch (Exception ex) {
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		}
	}

	/**
	 * 
	 * @param uriInfo
	 *            localhost:8080/waypoints-ws/usuario/login/
	 * @return
	 */
	// @POST
	// @Path("login")
	// @Produces(MediaType.APPLICATION_JSON)
	// public Response getUsuarios(Usuario usuario) {
	// usuarioFA.login();
	// List<Usuario> usuarios = new ArrayList<>();
	// Usuario u1, u2, u3;
	// u1 = new Usuario();
	// u2 = new Usuario();
	// u3 = new Usuario();
	// u1.setNome("Jhony");
	// u2.setNome("Romulo");
	// u3.setNome("Romisvaldo");
	// usuarios.add(u1);
	// usuarios.add(u2);
	// usuarios.add(u3);
	// return Response.ok().entity(usuarios.stream().filter((batata) -> {
	// return
	// batata.getNome().toLowerCase().contains(queryParams.getFirst("nome").toLowerCase());
	// }).collect(Collectors.toList())).build();
	// }

	// @GET
	// @Path("lista")
	// @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	// public String listaUsuarios() {
	// UsuarioFA.listaUsuarios();
	// List<Usuario> usuarios;
	//
	// UsuarioDAO usuarioDAO = new UsuarioDAO();
	// usuarios = usuarioDAO.listaUsuarios();
	//
	// return JSONUtil.getJSON(usuarios);
	// }

	// @GET
	// @Path("login/{login}/{senha}")
	// @Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	// public String login(@PathParam("login") String login, @PathParam("senha")
	// String senha) {
	// if ((login.equals("hustik") && senha.equals("romulogp"))) {
	// return "Login efetuado com sucesso!";
	// } else {
	// return "Login e/ou senha inválido(s).";
	// }
	// }
	//
	// @GET
	// @Path("usuario/lista")
	// @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	// public String listaUsuarios() {
	// List<Usuario> usuarios;
	//
	// UsuarioDAO usuarioDAO = new UsuarioDAO();
	// usuarios = usuarioDAO.listaUsuarios();
	// Gson gson = new Gson();
	// gson.toString();
	//
	// return gson.toJson(usuarios);
	// }
}
