package com.waypoints.webservice.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.sun.jersey.api.client.ClientResponse.Status;
import com.waypoints.entity.Usuario;
import com.waypoints.entity.Usuario.Sexo;
import com.waypoints.util.JSONUtil;

public class RestClient {

	public static void main(String[] args) {
//		login();
//		cadastroUsuario();
		findById();
	}

	private static void login() {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpPost postRequest = new HttpPost("http://localhost:8080/waypoints-ws/recursos/usuario/login");

			Usuario usuario = new Usuario();
			usuario.setEmail("batatinha2@gmail.com");
			usuario.setSenha("batatinha2");

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
	
	private static void cadastroUsuario() {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpPost postRequest = new HttpPost("http://localhost:8080/waypoints-ws/recursos/usuario/cadastro");

			Usuario usuario = new Usuario();
			usuario.setNome("Batatinha3");
			usuario.setEmail("batatinha3@gmail.com");
			usuario.setSenha("batatinha3");
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

	private static void findById() {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			Integer idToFind = 28;
			HttpGet postRequest = new HttpGet("http://localhost:8080/waypoints-ws/recursos/usuario/" + idToFind);

			HttpResponse response = httpClient.execute(postRequest);

			// Leitura da resposta vinda do servidor
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

			System.out.println("Status: " + response.getStatusLine().getStatusCode());
			String resposta;
			while ((resposta = br.readLine()) != null) {
				System.out.println("Servidor responde: " + resposta);
			}

		} catch (IOException e) {

		}
	}
	
}