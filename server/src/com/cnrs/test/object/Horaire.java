package com.cnrs.test.object;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
}
