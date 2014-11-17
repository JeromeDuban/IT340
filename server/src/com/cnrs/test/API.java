package com.cnrs.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cnrs.test.object.Atelier;
import com.google.common.net.HttpHeaders;


@Path("/atelier/")
public class API {

	Connection connection = null;
	PreparedStatement s = null;
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String newTodo(String input, @Context HttpServletResponse servletResponse, @Context HttpHeaders httpHeaders) {
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		servletResponse.setHeader("Access-Control-Max-Age", "3600");
		servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept");
		
		System.out.println("Input :" + input);
		
		Atelier atelier= new Atelier();
		
		try {
			connection = DriverManager.getConnection(Config.connectionURL, Config.usernameDB, Config.passwordDB);
			JSONObject json = new JSONObject(input);
			
			atelier.setId(json.getInt("id"));
			atelier.setTitle(json.getString("title"));
			atelier.setLab(json.getString("lab"));
			atelier.setTheme(json.getString("theme"));
			atelier.setLocation(json.getString("location"));
			atelier.setType(json.getString("type"));
			atelier.setDuration(json.getString("duration"));
			atelier.setCapacity(json.getString("capacity"));
			atelier.setSummary(json.getString("summary"));
			atelier.setAnim(json.getString("anim"));
			atelier.setPartners(json.getString("partners"));
			
			String queryInsert = "INSERT INTO `ateliers`"
					+ "(`atelier_ID`, `title`, `lab`, `theme`, `location`, `type`,"
					+ " `duration`, `capacity`, `summary`, `anim`, `partners`,"
					+ " `content`, `public_list`, `horaires_list`) VALUES"
					+ " ("+ atelier.getId() +","
					+ "\""+ atelier.getTitle() +"\","
					+ "\""+ atelier.getLab() +"\","
					+ "\""+ atelier.getTheme()+"\","
					+ "\""+ atelier.getLocation()+"\","
					+ "\""+ atelier.getType()+"\","
					+ "\""+ atelier.getDuration()+"\","
					+ "\""+ atelier.getCapacity()+"\","
					+ "\""+ atelier.getSummary()+"\","
					+ "\""+ atelier.getAnim()+"\","
					+ "\""+ atelier.getPartners()+"\","
					+ "\""+ atelier.getContent()+"\","
					+ "\"TBD\","
					+ "\"TBD\")";
			
			s = (PreparedStatement) connection.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS);
			s.executeUpdate();
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		
		return input;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String test(@Context HttpServletResponse servletResponse){
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		servletResponse.setHeader("Access-Control-Max-Age", "3600");
		servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept");
		
		try {
			connection = DriverManager.getConnection(Config.connectionURL, Config.usernameDB, Config.passwordDB);
			
			s = (PreparedStatement) connection.prepareStatement("SELECT * FROM `ateliers`", Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = s.executeQuery();
			
			return convertToJSON(rs).toString();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "ERROR";
	}
	
	@OPTIONS
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public void testOption(@Context HttpServletResponse servletResponse){
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		servletResponse.setHeader("Access-Control-Max-Age", "3600");
		servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept");
	}
	
	public static JSONArray convertToJSON(ResultSet resultSet)
            throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 0; i < total_rows; i++) {
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1));
            }
            jsonArray.put(obj);
        }
        return jsonArray;
    }

}
