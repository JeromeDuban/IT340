package com.cnrs.test.object;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cnrs.test.API;
import com.cnrs.test.Config;

public class Visitor extends TypeObj {

	public Visitor() {
		super();
	}

	public static ArrayList<Visitor> jsonArrayToArrayListVisitor(JSONArray jsonArray) throws JSONException{

		ArrayList<Visitor> arrayList = new ArrayList<Visitor>();
		JSONObject jsonObj;

		for (int i = 0; i < jsonArray.length(); i++) {
			Visitor visitor = new Visitor();
			jsonObj = (JSONObject) jsonArray.get(i);

			visitor.setId(jsonObj.getInt("id"));
			visitor.setName(jsonObj.getString("name"));
			arrayList.add(visitor);
		}

		return arrayList;
	}

	public static String getListId(ArrayList<Visitor> arrayListVisitor){

		ArrayList<String> parms = new ArrayList<String>();

		for (Iterator<Visitor> iterator = arrayListVisitor.iterator(); iterator
				.hasNext();) {
			Visitor visitor = (Visitor) iterator.next();
			parms.add(String.valueOf( visitor.getId() ));
		}

		return TypeObj.joinId(parms);
	}

	public static JSONArray constructJsonArrayVisitor(String rsString) throws JSONException, SQLException{

		ArrayList<Visitor> visitorArraylist = new ArrayList<Visitor>();

		for (String value: rsString.split(":")){
			Visitor visitor = new Visitor();
			visitor.setId(Integer.parseInt(value));
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				API.connection = DriverManager.getConnection(Config.connectionURL, Config.usernameDB, Config.passwordDB);

				try{
					API.s = (PreparedStatement) API.connection.prepareStatement("SELECT name FROM `visitors_list` WHERE `id`="+ Integer.parseInt(value),
							Statement.RETURN_GENERATED_KEYS);

					ResultSet rs = API.s.executeQuery();

					if(rs.first()){
						visitor.setName(rs.getString(1));		
					}else
						visitor.setName("ERROR");

					rs.close();
				}finally{
					if (API.s != null) API.s.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if (API.connection != null) API.connection.close();
			}   
			visitorArraylist.add(visitor);
		}
		JSONArray jsonArray = new JSONArray(visitorArraylist.toString());

		return jsonArray;
	}
}
