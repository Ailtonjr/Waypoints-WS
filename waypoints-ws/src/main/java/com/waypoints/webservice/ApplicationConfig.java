package com.waypoints.webservice;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("recursos")
public class ApplicationConfig extends Application {
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}
	
	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(com.waypoints.webservice.DemoRest.class);
		resources.add(com.waypoints.webservice.UsuarioController.class);
	}
	
}
