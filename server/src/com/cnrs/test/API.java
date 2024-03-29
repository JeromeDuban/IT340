package com.cnrs.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cnrs.test.object.Atelier;
import com.cnrs.test.object.Horaire;
import com.cnrs.test.object.Visitor;
import com.google.common.net.HttpHeaders;


@Path("/ateliers/")
public class API {

	public static Connection connection = null;
	public static PreparedStatement s = null;

	/* ajouter un atelier */

	@POST
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public boolean create(String input, @Context HttpServletResponse servletResponse, @Context HttpHeaders httpHeaders) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		int affectedRows = 0;

		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		servletResponse.setHeader("Access-Control-Max-Age", "3600");
		servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept");

		System.out.println("CREATE > Input :" + input);

		Atelier atelier= new Atelier();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(Config.connectionURL, Config.usernameDB, Config.passwordDB);
			JSONObject json = new JSONObject(input);

//			atelier.setId(json.getInt("id"));
			atelier.setTitle(json.getString("title"));
			atelier.setLab(json.getString("lab"));
			atelier.setTheme(json.getString("theme"));
			atelier.setLocation(json.getString("location"));
			atelier.setType(json.getString("type"));
			atelier.setDuration(json.getString("duration"));
			atelier.setCapacity(json.getString("capacity"));
			atelier.setSummary(json.getString("summary"));
			atelier.setAnim(json.getString("anim"));
			atelier.setContent(json.getString("content"));
			atelier.setPartners(json.getString("partners"));

			/* Set type of Public */
			atelier.setVisitorsList(Visitor.getListId(Visitor.jsonArrayToArrayListVisitor(json.getJSONArray("visitors"))));
			/* Set type of Horaires */
			atelier.setHorairesList(Horaire.getListId(Horaire.jsonArrayToArrayListHoraire(json.getJSONArray("horaires"))));

			String queryInsert = "INSERT INTO `ateliers`"
					+ "(`title`, `lab`, `theme`, `location`, `type`,"
					+ " `duration`, `capacity`, `summary`, `anim`, `partners`,"
					+ " `content`, `visitors`, `horaires`) VALUES"
					+ " ("/*+ atelier.getId() +","*/
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
					+ "\""+atelier.getVisitorsList()+"\","
					+ "\""+atelier.getHorairesList()+"\")";

			try{
				s = (PreparedStatement) connection.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS);
				affectedRows = s.executeUpdate();

				System.out.println(Integer.toString(affectedRows));
				if (affectedRows == 1){
					s.close();
					connection.close();
					return true;
				}
			}finally{
				if (s != null) s.close();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	/* Editer un atelier */

	@POST
	@Path("/update/")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public boolean update(String input, @Context HttpServletResponse servletResponse, @Context HttpHeaders httpHeaders) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		int affectedRows = 0;

		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		servletResponse.setHeader("Access-Control-Max-Age", "3600");
		servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept");

		System.out.println("UPDATE > Input :" + input);

		Atelier atelier= new Atelier();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
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

			atelier.setVisitorsList(Visitor.getListId(Visitor.jsonArrayToArrayListVisitor(json.getJSONArray("visitors"))));
			atelier.setHorairesList(Horaire.getListId(Horaire.jsonArrayToArrayListHoraire(json.getJSONArray("horaires"))));

			String queryUpdate = "UPDATE `ateliers` SET "
					+ "`title`=\""+ atelier.getTitle() +"\","
					+ "`lab`=\""+ atelier.getLab() +"\","
					+ "`theme`=\""+ atelier.getTheme() +"\","
					+ "`location`=\""+ atelier.getLocation() +"\","
					+ "`type`=\""+ atelier.getType() +"\","
					+ "`duration`=\""+ atelier.getDuration() +"\","
					+ "`capacity`=\""+ atelier.getCapacity() +"\","
					+ "`summary`=\""+ atelier.getSummary() +"\","
					+ "`anim`=\""+ atelier.getAnim() +"\","
					+ "`partners`=\""+ atelier.getPartners() +"\","
					+ "`content`=\""+ atelier.getContent() +"\","
					+ "`visitors`=\"" + atelier.getVisitorsList() +"\","
					+ "`horaires`=\"" + atelier.getHorairesList() +"\""
					+ "WHERE `id`="+atelier.getId();
			try{
				s = (PreparedStatement) connection.prepareStatement(queryUpdate, Statement.RETURN_GENERATED_KEYS);
				affectedRows = s.executeUpdate();

				if (affectedRows == 1){
					s.close();
					connection.close();
					return true;
				}
			}finally{
				if (s != null) s.close();
			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println(Integer.toString(affectedRows));
		return false;
	}

	/* Lister les ateliers */
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getAll(@Context HttpServletResponse servletResponse) throws SQLException{

		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		servletResponse.setHeader("Access-Control-Max-Age", "3600");
		servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept");

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(Config.connectionURL, Config.usernameDB, Config.passwordDB);

			try{
				s = (PreparedStatement) connection.prepareStatement("SELECT * FROM `ateliers`", Statement.RETURN_GENERATED_KEYS);

				ResultSet rs = s.executeQuery();
				String rss = convertToJSON(rs).toString();

				rs.close();
				s.close();
				connection.close();
				return rss;
			}finally{
				if (s != null) s.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (connection != null) connection.close();
		}

		return "ERROR";
	}

	/* Demander un atelier */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getAtelier(@PathParam("id") String id, @Context HttpServletResponse servletResponse){
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		servletResponse.setHeader("Access-Control-Max-Age", "3600");
		servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept");

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(Config.connectionURL, Config.usernameDB, Config.passwordDB);

			try{
				s = (PreparedStatement) connection.prepareStatement("SELECT * FROM `ateliers` WHERE `id`="+ id, Statement.RETURN_GENERATED_KEYS);
				ResultSet rs = s.executeQuery();
				String rss = convertToJSON(rs).toString();

				rs.close();
				s.close();
				connection.close();
				return rss;			
			}finally{
				if (s != null) s.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return "ERROR";
	}

	/*  Demander liste des visiteurs et cr�naux possibles  */
	@GET
	@Path("/listVisitorsHoraires/")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getListVisitorsHoraires(@Context HttpServletResponse servletResponse){
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		servletResponse.setHeader("Access-Control-Max-Age", "3600");
		servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept");

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(Config.connectionURL, Config.usernameDB, Config.passwordDB);

			try{
				s = (PreparedStatement) connection.prepareStatement("SELECT * FROM visitors_list", Statement.RETURN_GENERATED_KEYS);
				ResultSet rs = s.executeQuery();
				String visitorsList = convertToJSON(rs).toString();
				JSONArray visitorsArray = new JSONArray(visitorsList);

				s = (PreparedStatement) connection.prepareStatement("SELECT * FROM horaires_list", Statement.RETURN_GENERATED_KEYS);
				ResultSet rs1 = s.executeQuery();
				String horairesList = convertToJSON(rs1).toString();
				JSONArray horairesArray = new JSONArray(horairesList);

				JSONObject json = new JSONObject();
				json.put("visitors", visitorsArray);
				json.put("horaires", horairesArray);

				rs.close();
				rs1.close();
				s.close();
				connection.close();
				return json.toString();			
			}finally{
				if (s != null) s.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return "ERROR";
	}

	/* Supprimer un atelier */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public boolean delete(String input, @Context HttpServletResponse servletResponse){

		int affectedRows = 0;

		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		servletResponse.setHeader("Access-Control-Max-Age", "3600");
		servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept");

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(Config.connectionURL, Config.usernameDB, Config.passwordDB);
			JSONObject json = new JSONObject(input);

			int id = json.getInt("id");

			String queryDelete = "DELETE FROM `ateliers` WHERE `id`="+ Integer.toString(id);

			System.out.println(queryDelete);

			try{
				s = (PreparedStatement) connection.prepareStatement(queryDelete, Statement.RETURN_GENERATED_KEYS);
				affectedRows = s.executeUpdate();

				System.out.println(Integer.toString(affectedRows));
				if (affectedRows == 1){
					s.close();
					connection.close();
					return true;
				}

			}finally{
				if (s != null) s.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	@OPTIONS
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public void option(@Context HttpServletResponse servletResponse){
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		servletResponse.setHeader("Access-Control-Max-Age", "3600");
		servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept");
	}

	@OPTIONS
	@Path("/update/")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public void optionUpdate(@Context HttpServletResponse servletResponse){
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		servletResponse.setHeader("Access-Control-Max-Age", "3600");
		servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept");
	}

	/**
	 * Convert a result set into a JSON Array
	 * @param resultSet
	 * @return a JSONArray
	 * @throws Exception
	 */
	public static JSONArray convertToJSON(ResultSet resultSet)
			throws Exception {
		JSONArray jsonArray = new JSONArray();
		while (resultSet.next()) {
			int total_rows = resultSet.getMetaData().getColumnCount();
			JSONObject obj = new JSONObject();
			for (int i = 0; i < total_rows; i++) {
				if (resultSet.getMetaData().getColumnLabel(i + 1).equalsIgnoreCase("visitors")) {					
					JSONArray ja = Visitor.constructJsonArrayVisitor(resultSet.getString(i + 1));
					obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
							.toLowerCase(), ja);
				}
				else if (resultSet.getMetaData().getColumnLabel(i + 1).equalsIgnoreCase("horaires")) {					
					JSONArray ja = Horaire.constructJsonArrayHoraire(resultSet.getString(i + 1));
					obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
							.toLowerCase(), ja);
				}
				else{
					obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
							.toLowerCase(), resultSet.getObject(i + 1));
				}
			}
			jsonArray.put(obj);
		}
		return jsonArray;
	}

}
