package com.waypoints.webservice.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.waypoints.entity.Usuario;
import com.waypoints.entity.Usuario.Sexo;
import com.waypoints.util.JSONUtil;

public class RestClient {

	public static void main(String[] args) {
		testeLogin();
	}

	private static void testeLogin() {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpPost postRequest = new HttpPost("http://localhost:8080/waypoints-ws/recursos/usuario/login");

			Usuario usuario = new Usuario();
			usuario.setEmail("romulogoelzer@gmail.com");
			usuario.setSenha("teste");

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
	
	@SuppressWarnings("unused")
	private static void cadastroCliente() {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpPost postRequest = new HttpPost("http://localhost:8080/waypoints-ws/recursos/usuario/cadastro");

			Usuario usuario = new Usuario();
			usuario.setNome("Teste");
			usuario.setEmail("teste@gmail.com");
			usuario.setSenha("teste");
			usuario.setCategoriaCNH("AB");
			System.out.println(Calendar.getInstance().getTime());
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

}