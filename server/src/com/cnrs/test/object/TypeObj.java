package com.cnrs.test.object;

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
	
}
