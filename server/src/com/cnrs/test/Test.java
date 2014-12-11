package com.cnrs.test;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cnrs.test.object.Atelier;
import com.cnrs.test.object.Visitor;


public class Test {

	
	
	public static void main(String[] args) {
	
		String test = "{id:1,title:\"titre1\","
				+ "lab:\"LaBRI\","
				+ " theme:\"Theme1\","
				+ " location:\"CNRS, TALENCE\","
				+ " type: \"type1\","
				+ " duration:\"1h30\","
				+ " capacity:\"120\","
				+ " summary:\"Some stuff happening somewhere\","
				+ " anim:\"Marc Fgrijzd\","
				+ " partners:\"LaBRI\","
				+ " content:\"IT\","
				+ " visitors : [{id : 1, name : \"Primaire\"}, {id : 8, name : \"Universite\"}],"
				+ " horaires : [\"1\", \"10\"]}";
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
			
			JSONArray publicJArray = json.getJSONArray("visitors");

			ArrayList<Visitor> publicArrayList = new ArrayList<Visitor>();
			JSONObject jj;
			for (int i = 0; i < publicJArray.length(); i++) {
				Visitor visitor = new Visitor();
				jj = (JSONObject) publicJArray.get(i);

				visitor.setId(jj.getInt("id"));
				visitor.setName(jj.getString("name"));
				publicArrayList.add(visitor);
//				System.out.println("publicArrayList = " + publicArrayList.toString());
			}
			atelier.setPublic_list(publicArrayList);
			
//			String vis = publicJArray.toString();
//			System.out.println("vis = "+vis);
			
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
					+ "\""+atelier.getPublic_list()+"\","
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
					+ "`public_list`=\"" + atelier.getPublic_list() +"\","
					+ "WHERE `atelier_ID`="+atelier.getId();
			
			System.out.println(queryInsert);
			System.out.println(queryUpdate);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
