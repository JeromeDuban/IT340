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
public class DeleteTables {

	
	@GET
	@Path("/delete/")												// initialisation de la base de données
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String init() { 
		
		System.out.println("Init started");
				
		String queryAtelier="DROP table ateliers;";
		String queryPublic="DROP table visitors_list;";
		String queryHoraires="DROP table horaires_list;";
		

		
		
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