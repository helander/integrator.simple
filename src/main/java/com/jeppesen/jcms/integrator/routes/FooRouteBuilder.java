package com.jeppesen.jcms.integrator.routes;

import org.apache.camel.builder.RouteBuilder;

public class FooRouteBuilder extends RouteBuilder {
	
	public void configure() {
				
		from("direct:Foo")
			.to("mock:Foo")
			;
		
	}			  
}

	
