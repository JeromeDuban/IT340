package com.cnrs.test;

import org.json.JSONException;
import org.json.JSONObject;

import com.cnrs.test.object.Atelier;


public class Test {

	
	
	public static void main(String[] args) {
	
		String test = "{id:1,title:\"titre1\",lab:\"LaBRI\", theme:\"Theme1\", location:\"CNRS, TALENCE\", type: \"type1\", duration:\"1h30\", capacity:\"120\", summary:\"Some stuff happening somewhere\", anim:\"Marc Fgrijzd\", partners:\"LaBRI\", content:\"IT\", visitors : [{name : \"Lycée\", checked: true}, {name : \"Collège\", checked: false}], horaires : [{name:\"Mercredi matin\", checked: true}, {name : \"Jeudi Après-midi\", checked: false}]}";
		Atelier atelier= new Atelier();
		
		
		try {
			JSONObject json = new JSONObject(test);
			
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
			atelier.setContent(json.getString("content"));
			
			System.out.println(atelier.toString());
			
			String queryInsert = "INSERT INTO `ateliers`"
					+ "(`atelier_ID`, `title`, `lab`, `theme`, `location`, `type`,"
					+ " `duration`, `capacity`, `summary`, `anim`, `partners`,"
					+ " `content`, `public_list`, `horaires_list`) VALUES"
					+ " ("+atelier.getId() +","
					+ "\""+ atelier.getTitle() +"\","
					+ "\""+ atelier.getLab() +"\","
					+ "\""+atelier.getTheme()+"\","
					+ "\""+atelier.getLocation()+"\","
					+ "\""+atelier.getType()+"\","
					+ "\""+atelier.getDuration()+"\","
					+ "\""+atelier.getCapacity()+"\","
					+ "\""+atelier.getSummary()+"\","
					+ "\""+atelier.getAnim()+"\","
					+ "\""+atelier.getPartners()+"\","
					+ "\""+atelier.getContent()+"\","
					+ "\"TBD\","
					+ "\"TBD\")";
			
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
					+ "WHERE `atelier_ID`="+atelier.getId();
			
			System.out.println(queryInsert);
			System.out.println(queryUpdate);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
