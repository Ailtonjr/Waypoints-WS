package com.waypoints.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {
	// LOCALHOST
//	 private static final String JDBC     = "postgresql";
//	 private static final String HOST     = "//localhost";
//	 private static final String PORT     = "5432";
//	 private static final String DATABASE = "db_teste";
//	 private static final String USER     = "u_teste";
//	 private static final String PASSWORD = "teste";
//	 private static final String URL      = "jdbc:" + JDBC + ":" + HOST + ":" +
//	 PORT + "/" + DATABASE + "?user=" + USER + "&password=" + PASSWORD;
	
	// HEROKU
	private static final String JDBC     = "postgresql";
	private static final String HOST     = "ec2-107-22-246-250.compute-1.amazonaws.com";
	private static final String PORT     = "5432";
	private static final String DATABASE = "dcm5eh2251lvj3";
	private static final String USER     = "epasgwxmjutyte";
	private static final String PASSWORD = "PxS7y1VanvKpkRc9kekLPpm6hx";
	private static final String DRIVER   = "org.postgresql.Driver";
	
	private static Connection connection = null;

	public static Connection getConexao() {
		System.out.println("-------- Teste de Conexão JDBC PostgreSQL ------------");

		try {

			Class.forName(DRIVER);

		} catch (ClassNotFoundException e) {
			System.out.println("Não foi possível localizar o Driver JDBC.!");
			e.printStackTrace();
			return null;
		}

		System.out.println("Driver JDBC PostgreSQL Registrado!");

		try {
//----------HEROKU DATABASE----------------------------------------------------------------
			String url = "jdbc:" + JDBC + "://"
					+ HOST + ":" + PORT + "/"
					+ DATABASE;
			Properties props = new Properties();
			props.setProperty("user", USER);
			props.setProperty("password", PASSWORD);
			props.setProperty("ssl","true");
			props.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
			connection = DriverManager.getConnection(url, props);
//-----------------------------------------------------------------------------------------
		} catch (SQLException e) {
			System.out.println("Falha na conexão! Verifique o log.");
			e.printStackTrace();
			return null;
		}

		if (connection != null) {
			System.out.println("Conectado!");
		} else {
			System.out.println("Falha na tentativa de conexão!");
		}
		return connection;
	}

	/**
	 * @param sql comando SQL para ser executado
	 * @return
	 */
	public static PreparedStatement getPreparedStatement(String sql) {
		if (connection == null) {
			connection = getConexao();
		}
		try {
			// Retorna um objeto java.sql.PreparedStatement
			return connection.prepareStatement(sql);

		} catch (SQLException e) {
			System.out.println("Erro de sql: " + e.getMessage());
		}
		return null;
	}

}