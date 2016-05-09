package com.waypoints.webservice.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.waypoints.entity.Grupo;
import com.waypoints.entity.Integrante;
import com.waypoints.entity.Usuario;
import com.waypoints.entity.Usuario.Sexo;
import com.waypoints.util.JSONUtil;

public class RestClient {

	public static void main(String[] args) {
//		login();
//		cadastroUsuario();
//		alterarCadastro();
//		findById(40);
		findByEmail("romulogoelzer@gmail.com");
//		excluirUsuario();
//		teste();
//		cadastroGrupo();
//		alterarGrupo();
//		removerGrupo();
//		findGrupoById(14);
	}
	
	private static void findGrupoById(int id) {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpGet postRequest = new HttpGet("http://localhost:8080/waypoints-ws/recursos/grupo/" + id);

	        HttpResponse response = httpClient.execute(postRequest);
	        
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

			String resposta;
			System.out.println("Status: " + response.getStatusLine().getStatusCode());
			while ((resposta = br.readLine()) != null) {
				System.out.println("Servidor responde: " + resposta);
			}

		} catch (IOException e) {

		}
	}
	
	private static void removerGrupo() {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpPost postRequest = new HttpPost("http://localhost:8080/waypoints-ws/recursos/grupo/excluir");

			Grupo g = new Grupo();
			g.setId(new Long(14));
			g.setNome("Its Over 9 thousand");
			g.setNomeProprietario("ProprietarioTeste");
			g.setRamo("RamoTeste");
			
			String grupoJSON = JSONUtil.getJSON(g);
	        StringEntity entity = new StringEntity(grupoJSON, ContentType.APPLICATION_JSON);
	        postRequest.setEntity(entity);
	        HttpResponse response = httpClient.execute(postRequest);
	        
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

			String resposta;
			System.out.println("Status: " + response.getStatusLine().getStatusCode());
			while ((resposta = br.readLine()) != null) {
				System.out.println("Servidor responde: " + resposta);
			}

		} catch (IOException e) {

		}
	}
	
	private static void alterarGrupo() {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpPost postRequest = new HttpPost("http://localhost:8080/waypoints-ws/recursos/grupo/alterar");

			Usuario u1 = new Usuario();
			u1.setId(new Long(8));
			u1.setNome("Rômulo Göelzer Portolann");
			u1.setEmail("romulogoelzer@gmail.com");
			u1.setCategoriaCNH("AB");
			u1.setSexo(Sexo.M);
			
			Usuario u2 = new Usuario();
			u2.setId(new Long(33));
			u2.setNome("Teste2");
			u2.setEmail("teste2@gmail.com");
			u2.setCategoriaCNH("AB");
			u2.setSexo(Sexo.M);
			
			Integrante i1 = new Integrante();
			i1.setUsuario(u1);
			i1.setPapel("ADMIN");

			Integrante i2 = new Integrante();
			i2.setUsuario(u2);
			i2.setPapel("PLANEJADOR");
			
			List<Integrante> integrantes = new ArrayList<>();
			integrantes.add(i1);
			integrantes.add(i2);
			
			Grupo g = new Grupo();
			g.setId(new Long(13));
			g.setNome("Its Over 9 thousand");
			g.setNomeProprietario("ProprietarioTeste");
			g.setProprietarioId(u1.getId());
			g.setRamo("RamoTeste");
			g.setIntegrantes(integrantes);
			
			String grupoJSON = JSONUtil.getJSON(g);
	        StringEntity entity = new StringEntity(grupoJSON, ContentType.APPLICATION_JSON);
	        postRequest.setEntity(entity);
	        HttpResponse response = httpClient.execute(postRequest);
	        
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

			String resposta;
			System.out.println("Status: " + response.getStatusLine().getStatusCode());
			while ((resposta = br.readLine()) != null) {
				System.out.println("Servidor responde: " + resposta);
			}

		} catch (IOException e) {

		}
	}
	
	private static void cadastroGrupo() {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpPost postRequest = new HttpPost("http://localhost:8080/waypoints-ws/recursos/grupo/cadastro");

			Usuario u1 = new Usuario();
			u1.setId(new Long(8));
			u1.setNome("Rômulo Göelzer Portolann");
			u1.setEmail("romulogoelzer@gmail.com");
			u1.setCategoriaCNH("AB");
			u1.setSexo(Sexo.M);
			
			Usuario u2 = new Usuario();
			u2.setId(new Long(33));
			u2.setNome("Teste2");
			u2.setEmail("teste2@gmail.com");
			u2.setCategoriaCNH("AB");
			u2.setSexo(Sexo.M);
			
			Integrante i1 = new Integrante();
			i1.setUsuario(u1);
			i1.setPapel("ADMIN");

			Integrante i2 = new Integrante();
			i2.setUsuario(u2);
			i2.setPapel("PLANEJADOR");
			
			List<Integrante> integrantes = new ArrayList<>();
			integrantes.add(i1);
			integrantes.add(i2);
			
			Grupo g = new Grupo();
			g.setNome("GrupoTeste100");
			g.setNomeProprietario(u1.getNome());
			g.setProprietarioId(u1.getId());
			g.setRamo("RamoTeste100");
			g.setIntegrantes(integrantes);
			
			String grupoJSON = JSONUtil.getJSON(g);
	        StringEntity entity = new StringEntity(grupoJSON, ContentType.APPLICATION_JSON);
	        postRequest.setEntity(entity);
	        HttpResponse response = httpClient.execute(postRequest);
	        
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

			String resposta;
			System.out.println("Status: " + response.getStatusLine().getStatusCode());
			while ((resposta = br.readLine()) != null) {
				System.out.println("Servidor responde: " + resposta);
			}

		} catch (IOException e) {

		}

	}

	private static void login() {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpPost postRequest = new HttpPost("http://localhost:8080/waypoints-ws/recursos/usuario/login");

			Usuario usuario = new Usuario();
			usuario.setEmail("teste2@gmail.com");
			usuario.setSenha("teste2teste2");

			// O servidor espera uma entidade JSON
			String usuarioJson = JSONUtil.getJSON(usuario);
			StringEntity jsonEntity = new StringEntity(usuarioJson);
			jsonEntity.setContentType("application/json");
			postRequest.setEntity(jsonEntity);

			HttpResponse response = httpClient.execute(postRequest);

			// Leitura da resposta vinda do servidor
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

			String resposta;
			System.out.println("Status: " + response.getStatusLine().getStatusCode());
			while ((resposta = br.readLine()) != null) {
				System.out.println("Servidor responde: " + resposta);
			}

		} catch (IOException e) {

		}
	}
	
	private static void cadastroUsuario() {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpPost postRequest = new HttpPost("http://localhost:8080/waypoints-ws/recursos/usuario/cadastro");

			Usuario usuario = new Usuario();
			usuario.setNome("teste1234");
			usuario.setEmail("teste1234@gmail.com");
			usuario.setSenha("teste1234");
			usuario.setCategoriaCNH("AB");
			usuario.setSexo(Sexo.M);
			

			// O servidor espera uma entidade JSON
			String usuarioJson = JSONUtil.getJSON(usuario);
			StringEntity jsonEntity = new StringEntity(usuarioJson);
			jsonEntity.setContentType("application/json");
			postRequest.setEntity(jsonEntity);

			HttpResponse response = httpClient.execute(postRequest);

			// Leitura da resposta vinda do servidor
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

			String resposta;
			System.out.println("Status: " + response.getStatusLine().getStatusCode());
			while ((resposta = br.readLine()) != null) {
				System.out.println("Servidor responde: " + resposta);
			}

		} catch (IOException e) {

		}
	}
	
	private static void alterarCadastro() {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpPost postRequest = new HttpPost("http://localhost:8080/waypoints-ws/recursos/usuario/alterar");

			Usuario usuario = new Usuario();
			usuario.setId(new Long(40));
			usuario.setNome("Teste");
			usuario.setEmail("finalteste@gmail.com");
			usuario.setSenha("finalteste");
			usuario.setCategoriaCNH("AB");
//			usuario.setDataNascimento(new Date("21/09/1993").);
			usuario.setSexo(Sexo.M);
			

			// O servidor espera uma entidade JSON
			String usuarioJson = JSONUtil.getJSON(usuario);
			StringEntity jsonEntity = new StringEntity(usuarioJson);
			jsonEntity.setContentType("application/json");
			postRequest.setEntity(jsonEntity);

			HttpResponse response = httpClient.execute(postRequest);

			// Leitura da resposta vinda do servidor
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

			String resposta;
			System.out.println("Status: " + response.getStatusLine().getStatusCode());
			while ((resposta = br.readLine()) != null) {
				System.out.println("Servidor responde: " + resposta);
			}

		} catch (IOException e) {

		}
	}
	
	private static void excluirUsuario() {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpPost postRequest = new HttpPost("http://localhost:8080/waypoints-ws/recursos/usuario/excluir");

			Usuario usuario = new Usuario();
			usuario.setId(new Long(35));
			usuario.setEmail("hue@gmail.com");
			usuario.setSenha("huehuehue");

			// O servidor espera uma entidade JSON
			String usuarioJson = JSONUtil.getJSON(usuario);
			StringEntity jsonEntity = new StringEntity(usuarioJson);
			jsonEntity.setContentType("application/json");
			postRequest.setEntity(jsonEntity);

			HttpResponse response = httpClient.execute(postRequest);

			// Leitura da resposta vinda do servidor
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

			String resposta;
			System.out.println("Status: " + response.getStatusLine().getStatusCode());
			while ((resposta = br.readLine()) != null) {
				System.out.println("Servidor responde: " + resposta);
			}

		} catch (IOException e) {

		}
	}
	
	private static void findById(int id) {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpGet postRequest = new HttpGet("http://localhost:8080/waypoints-ws/recursos/usuario/" + id);

			HttpResponse response = httpClient.execute(postRequest);

			// Leitura da resposta vinda do servidor
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

			String resposta;
			System.out.println("Status: " + response.getStatusLine().getStatusCode());
			while ((resposta = br.readLine()) != null) {
				System.out.println("Servidor responde: " + resposta);
			}

		} catch (IOException e) {

		}
	}
	
	private static void findByEmail(String email) {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpGet postRequest = new HttpGet("http://localhost:8080/waypoints-ws/recursos/usuario/email/" + email);

			HttpResponse response = httpClient.execute(postRequest);

			// Leitura da resposta vinda do servidor
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

			String resposta;
			System.out.println("Status: " + response.getStatusLine().getStatusCode());
			while ((resposta = br.readLine()) != null) {
				System.out.println("Servidor responde: " + resposta);
			}

		} catch (IOException e) {

		}
	}
	
}