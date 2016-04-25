package com.waypoints.webservice.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.ws.rs.core.Response.Status;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.waypoints.entity.Usuario;
import com.waypoints.entity.Usuario.Sexo;
import com.waypoints.util.JSONUtil;

public class RestClient {

	public static void main(String[] args) {
		login();
//		cadastroUsuario();
//		findById(30);
		alterarCadastro();
//		excluirUsuario();
	}

	private static void login() {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpPost postRequest = new HttpPost("http://localhost:8080/waypoints-ws/recursos/usuario/login");

			Usuario usuario = new Usuario();
			usuario.setEmail("teste2@gmail.com");
			usuario.setSenha("teste2teste");

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
			usuario.setNome("Teste3");
			usuario.setEmail("teste3@gmail.com");
			usuario.setSenha("teste3teste3");
			usuario.setCategoriaCNH("AB");
//			System.out.println(Calendar.getInstance().getTime());
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
	
	private static void alterarCadastro() {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpPost postRequest = new HttpPost("http://localhost:8080/waypoints-ws/recursos/usuario/alterar");

			Usuario usuario = new Usuario();
			usuario.setId(new Long(32));
			usuario.setNome("Batatinha5");
			usuario.setEmail("batatinha5@gmail.com");
			usuario.setSenha("batatinha5");
			usuario.setCategoriaCNH("AB");
//			System.out.println(Calendar.getInstance().getTime());
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
			usuario.setId(new Long(29));
			usuario.setEmail("batatinha@gmail.com");
			usuario.setSenha("batatinha5");

			// O servidor espera uma entidade JSON
			String usuarioJson = JSONUtil.getJSON(usuario);
			StringEntity jsonEntity = new StringEntity(usuarioJson);
			jsonEntity.setContentType("application/json");
			postRequest.setEntity(jsonEntity);

			HttpResponse response = httpClient.execute(postRequest);

			// Leitura da resposta vinda do servidor
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

			String resposta;
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
	
}