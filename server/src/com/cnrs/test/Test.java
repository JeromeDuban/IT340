package com.cnrs.test;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cnrs.test.object.Atelier;
import com.cnrs.test.object.Horaire;
import com.cnrs.test.object.TypeObj;
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
				+ " horaires : [{id : 1, name : \"Lundi matin\"}, {id : 10, name : \"Vendredi après-midi\"}]}";
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
			
			/* 
			 * Type of visitors/horaires
			 *  */			
//			atelier.setPublic_list(Visitor.jsonArrayToArrayListVisitor(json.getJSONArray("visitors")));
//			System.out.println(atelier.getPublic_list());
//			atelier.setHoraires_list(Horaire.jsonArrayToArrayListHoraire(json.getJSONArray("horaires")));
//			System.out.println(atelier.getHoraires_list());
			
			/* ArrayList<Visitor> to String a:b:c */
			ArrayList<Visitor> arrayListVisitor = Visitor.jsonArrayToArrayListVisitor(json.getJSONArray("visitors"));
			ArrayList<String> parms = new ArrayList<String>();
			for (Iterator iterator = arrayListVisitor.iterator(); iterator
					.hasNext();) {
				Visitor visitor = (Visitor) iterator.next();
				parms.add(String.valueOf( visitor.getId() ));
			}
			String sep = ""; StringBuffer b = new StringBuffer();
			for (String p: parms) {
			    b.append(sep);
			    b.append(p);
			    sep = ":";
//			    System.out.println(b.toString());
			}/* END ArrayList<Visitor> to String a:b:c */
			
			Visitor visitor = new Visitor();
			String idArrayList = b.toString();
			for (String value: idArrayList.split(":")){
		         visitor.setId(Integer.parseInt(value));
		         System.out.println("----For id = "+ visitor.getId());
		         
		         
		      }
			
			/*
			 *  End Type of visitors/horaires
			 *   */
			
			
			/*
			 *  Test convertToJson
			 *   */
//			String ts = "[{id : 1, name : \"Primaire\"}, {id : 8, name : \"Universite\"}]";
//			JSONArray jjA = new JSONArray(ts);
//			System.out.println("jjA= " +jjA);
//			if (ts.startsWith("[")) {
//				System.out.println("plop");
//			}
//			String tt = "{id:1,title:\"titre1\","
//					+ "lab:\"LaBRI\","
//					+ " theme:\"Theme1\","
//					+ " location:\"CNRS, TALENCE\","
//					+ " type: \"type1\","
//					+ " duration:\"1h30\","
//					+ " capacity:\"120\","
//					+ " summary:\"Some stuff happening somewhere\","
//					+ " anim:\"Marc Fgrijzd\","
//					+ " partners:\"LaBRI\","
//					+ " content:\"IT\"}";
//			JSONObject jo = new JSONObject(tt);
//			System.out.println("before jo= " + jo);
//			jo.put("visitors", jjA);
//			System.out.println("after jo= " + jo);
//			System.out.println(jo.getJSONArray("visitors"));
			/*
			 *  End Test convertToJson
			 *   */
			
			
//			String vis = publicJArray.toString();
//			System.out.println("vis = "+vis);
			
//			System.out.println(atelier.toString());
			
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
					+ "\""+atelier.getVisitorsList()+"\","
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
					+ "`public_list`=\"" + atelier.getVisitorsList() +"\","
					+ "WHERE `atelier_ID`="+atelier.getId();
			
//			System.out.println(queryInsert);
//			System.out.println(queryUpdate);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<TypeObj> jsonArrayToArrayListTypeObj(JSONArray jsonArray) throws JSONException{
		
		ArrayList<TypeObj> arrayList = new ArrayList<TypeObj>();
		JSONObject jsonObj;
		
		for (int i = 0; i < jsonArray.length(); i++) {
			TypeObj typeObj = new TypeObj();
			jsonObj = (JSONObject) jsonArray.get(i);
			
			typeObj.setId(jsonObj.getInt("id"));
			typeObj.setName(jsonObj.getString("name"));
			arrayList.add(typeObj);
		}
		
		return arrayList;
	}
	
	/* 
	 * Souvenir
	 * Set type of Public
	 *  */
//	JSONArray publicJArray = json.getJSONArray("visitors");
//	ArrayList<Visitor> publicArrayList = new ArrayList<Visitor>();
//	JSONObject jsonObject;
//	for (int i = 0; i < publicJArray.length(); i++) {
//		Visitor visitor = new Visitor();
//		jsonObject = (JSONObject) publicJArray.get(i);
//
//		visitor.setId(jsonObject.getInt("id"));
//		visitor.setName(jsonObject.getString("name"));
//		publicArrayList.add(visitor);
//	}
//	atelier.setPublic_list(publicArrayList);
	
	/* If it is an array */
	//            	if (resultSet.getString(i + 1).startsWith("[")) {
	//					String rs = resultSet.getString(i + 1);
	//					JSONArray rsJsonArray = new JSONArray(rs);
	//					obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
	//	                        .toLowerCase(), rsJsonArray);
	//				}
}
