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
				+ "id int NOT NULL PRIMARY KEY AUTO_INCREMENT,"
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
				+ "visitors varchar(1000),"
				+ "horaires varchar(1000)"
				+ ");";
		

		String queryPublic="CREATE TABLE IF NOT EXISTS visitors_list"
				+ "("
				+ "id int NOT NULL,"
				+ "name varchar(1000) NOT NULL,"
				+ "PRIMARY KEY (id)"
				+ ");";
		
		String queryHoraires ="CREATE TABLE IF NOT EXISTS horaires_list"
				+ "("
				+ "id int NOT NULL,"
				+ "name varchar(1000) NOT NULL,"
				+ "PRIMARY KEY (id)"
				+ ");";
		
		String queryAddPublic = "INSERT INTO visitors_list"
				+ "(`id`, `name`) VALUES"
				+ "(1, 'Primaire'),"
				+ "(2, '6ieme5ieme'),"
				+ "(3, '4ieme3ieme'),"
				+ "(4, '2nde'),"
				+ "(5, '1ere'),"
				+ "(6, 'Tale'),"
				+ "(7, 'Prepa'),"
				+ "(8, 'Universite')"
				+ ";";
		
		String queryAddHoraires = "INSERT INTO horaires_list"
				+ "(id, name) VALUES"
				+ "(1, 'Lundi matin'),"
				+ "(2, 'Lundi apres-midi'),"
				+ "(3, 'Mardi matin'),"
				+ "(4, 'Mardi apres-midi'),"
				+ "(5, 'Mercredi matin'),"
				+ "(6, 'Mercredi apres-midi'),"
				+ "(7, 'Jeudi matin'),"
				+ "(8, 'Jeudi apres-midi'),"
				+ "(9, 'Vendredi matin'),"
				+ "(10, 'Vendredi apres-midi')"
				+ ";";
		
		System.out.println(queryAddPublic);
		
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
			 
			 s = (PreparedStatement) connection.prepareStatement(queryAddPublic, Statement.RETURN_GENERATED_KEYS);
			 s.executeUpdate();
			 
			 s = (PreparedStatement) connection.prepareStatement(queryAddHoraires, Statement.RETURN_GENERATED_KEYS);
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
		}finally{
			try {
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println("After connection");
		return "Database set up";
	}
	
	
}