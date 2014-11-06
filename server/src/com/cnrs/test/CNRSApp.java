package com.cnrs.test;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


//Va lister les ressources disponibles dans le contexte /rest
@ApplicationPath("/rest")
public class CNRSApp extends Application{
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();	
		classes.add(Init.class);
		classes.add(Atelier.class);
		return classes;
	}
}
	