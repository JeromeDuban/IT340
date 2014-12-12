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

public class Horaire extends TypeObj{

	public Horaire() {
		super();
		// TODO Auto-generated constructor stub
	}


	public static ArrayList<Horaire> jsonArrayToArrayListHoraire(JSONArray jsonArray) throws JSONException{

		ArrayList<Horaire> arrayList = new ArrayList<Horaire>();
		JSONObject jsonObj;

		for (int i = 0; i < jsonArray.length(); i++) {
			Horaire horaire = new Horaire();
			jsonObj = (JSONObject) jsonArray.get(i);

			horaire.setId(jsonObj.getInt("id"));
			horaire.setName(jsonObj.getString("name"));
			arrayList.add(horaire);
		}

		return arrayList;
	}

	public static String getListId(ArrayList<Horaire> arrayListHoraire){

		ArrayList<String> parms = new ArrayList<String>();

		for (Iterator<Horaire> iterator = arrayListHoraire.iterator(); iterator
				.hasNext();) {
			Horaire horaire = (Horaire) iterator.next();
			parms.add(String.valueOf( horaire.getId() ));
		}

		return TypeObj.joinId(parms);
	}

	public static JSONArray constructJsonArrayHoraire(String rsString) throws JSONException{

		ArrayList<Horaire> horaireArraylist = new ArrayList<Horaire>();

		for (String value: rsString.split(":")){
			Horaire horaire = new Horaire();
			horaire.setId(Integer.parseInt(value));
			try {
				API.connection = DriverManager.getConnection(Config.connectionURL, Config.usernameDB, Config.passwordDB);

				API.s = (PreparedStatement) API.connection.prepareStatement("SELECT horaire FROM `horaires_list` WHERE `item_ID`="+ Integer.parseInt(value),
						Statement.RETURN_GENERATED_KEYS);
				ResultSet rs = API.s.executeQuery();
				if(rs.first()){
					horaire.setName(rs.getString(1));		
				}else
					horaire.setName("ERROR");

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}     
			horaireArraylist.add(horaire);
		}
		JSONArray jsonArray = new JSONArray(horaireArraylist.toString());

		return jsonArray;
	}
}
