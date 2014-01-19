package com.jeppesen.jcms.integrator.routes;

import org.apache.camel.builder.RouteBuilder;

public class BarRouteBuilder extends RouteBuilder {
	
	public void configure() {
				
		from("direct:Bar")
			.to("mock:Bar")
			;
		
	}			  
}

	
