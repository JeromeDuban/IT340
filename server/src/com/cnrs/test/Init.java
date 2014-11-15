package com.cnrs.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// Page contenant toutes les fonctions du serveur REST

@Path("/")
public class Init {

	

	@GET
	@Path("/init/")												// initialisation de la base de données
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String init() { 
		
		System.out.println("Init started");
				
		String queryAtelier="CREATE TABLE IF NOT EXISTS ateliers"
				+ "("
				+ "atelier_ID int NOT NULL PRIMARY KEY,"
				+ "title varchar(1000),"
				+ "lab varchar(1000),"
				+ "theme varchar(1000),"
				+ "location varchar(1000),"
				+ "type varchar(1000),"
				+ "duration varchar(1000),"
				+ "capacity varchar(1000),"
				+ "summary varchar(1000),"
				+ "anim varchar(1000),"
				+ "partners varchar(1000),"
				+ "content varchar(1000),"
				+ "public_list varchar(1000),"
				+ "horaires_list varchar(1000)"
				+ ");";
		

		String queryPublic="CREATE TABLE IF NOT EXISTS public_list"
				+ "("
				+ "item_ID int NOT NULL,"
				+ "public_ID int NOT NULL,"
				+ "atelier_ID int NOT NULL,"
				+ "PRIMARY KEY (item_ID),"
				+ "FOREIGN KEY (atelier_ID) REFERENCES ateliers(atelier_ID)"
				+ ");";
		
		String queryHoraires ="CREATE TABLE IF NOT EXISTS horaires_list"
				+ "("
				+ "item_ID int NOT NULL,"
				+ "horaire_ID int NOT NULL,"
				+ "atelier_ID int NOT NULL,"
				+ "PRIMARY KEY (item_ID),"
				+ "FOREIGN KEY (atelier_ID) REFERENCES ateliers(atelier_ID)"
				+ ");";
		
		Connection connection = null;
		PreparedStatement s = null;
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(Config.connectionURL, Config.usernameDB, Config.passwordDB);
			
			 s = (PreparedStatement) connection.prepareStatement(queryAtelier, Statement.RETURN_GENERATED_KEYS);
			 s.executeUpdate();
			 
			 s = (PreparedStatement) connection.prepareStatement(queryPublic, Statement.RETURN_GENERATED_KEYS);
			 s.executeUpdate();
			 
			 s = (PreparedStatement) connection.prepareStatement(queryHoraires, Statement.RETURN_GENERATED_KEYS);
			 s.executeUpdate();
			 
			 
		} catch (InstantiationException e){
			e.printStackTrace();
			return Arrays.toString(e.getStackTrace());
		} catch (SQLException e) {
			e.printStackTrace();
			return Arrays.toString(e.getStackTrace());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return Arrays.toString(e.getStackTrace());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return Arrays.toString(e.getStackTrace());
		}
		
		
		System.out.println("After connection");
		return "Database set up";
	}
	
	
}