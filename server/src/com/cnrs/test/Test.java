package com.cnrs.test;

import org.json.JSONException;
import org.json.JSONObject;


public class Test {

	
	
	public static void main(String[] args) {
	
		String test = "{id:1,title:\"titre1\",lab:\"LaBRI\", theme:\"Theme1\", location:\"CNRS, TALENCE\", type: \"type1\", duration:\"1h30\", capacity:\"120\", summary:\"Some stuff happening somewhere\", anim:\"Marc Fgrijzd\", partners:\"LaBRI\", content:\"IT\", visitors : [{name : \"Lycée\", checked: true}, {name : \"Collège\", checked: false}], horaires : [{name:\"Mercredi matin\", checked: true}, {name : \"Jeudi Après-midi\", checked: false}]}";

		try {
			JSONObject json = new JSONObject(test);
			System.out.println(json.get("id"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
