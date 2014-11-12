package com.cnrs.test;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.common.net.HttpHeaders;


@Path("/atelier/")
public class Atelier {

	@POST
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // working for media type == x-www-form-urlencoded
	//@Consumes(MediaType.APPLICATION_JSON)
	
	//public String newTodo(@FormParam("atelier") String atelier, @Context HttpServletResponse servletResponse, @Context HttpHeaders httpHeaders) {
	public String newTodo(String atelier, @Context HttpServletResponse servletResponse, @Context HttpHeaders httpHeaders) {
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		servletResponse.setHeader("Access-Control-Max-Age", "3600");
		servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept");
		
		return atelier;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String test(@Context HttpServletResponse servletResponse){
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		servletResponse.setHeader("Access-Control-Max-Age", "3600");
		servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept");
		return "GET is working";
	}
	
	@OPTIONS
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public void testOption(@Context HttpServletResponse servletResponse){
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		servletResponse.setHeader("Access-Control-Max-Age", "3600");
		servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept");
	}

}
