package com.cnrs.test.object;

public class Visitor {
	
	public int id;
	public String name;
	
	public Visitor() {
		super();
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
		return "{id : " + id + ", name : " + name + "}";
	}

}
