package com.cnrs.test.object;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

}
