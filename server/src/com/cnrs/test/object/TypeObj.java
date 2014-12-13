package com.cnrs.test.object;

import java.util.ArrayList;

public class TypeObj {

	public int id;
	public String name;
	
	public TypeObj() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", name=" + name + "}";
	}
	
	public static String joinId(ArrayList<String> parms){
		StringBuffer b = new StringBuffer();
		String sep = ""; 
		for (String p: parms) {
		    b.append(sep);
		    b.append(p);
		    sep = ":";
		}
		return b.toString();
	}
	
}
