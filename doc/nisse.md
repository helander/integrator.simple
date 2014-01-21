# samples.integrator.routes #

## Features ##

Provides a deployable ``integrator`` application (packaged as a WAR file), that contains a set of very basic Camel routes. Use this project as a starting point for your own integrator.

Routes are defined both in Java DSL and XML DSL. Replace these routes with your own routes when you are creating your own integrator project.

## Build ##

Build the component using 

    mvn clean install

## Configuration ##

### Java DSL routes ###

You define routes in Java by creating extensions of the Camel ``RouteBuilder`` class. Each such class needs to be listed in the ``WEB-INF/web.xml`` file.:

    <!-- Add Java route builders -->
    <context-param>
    	<param-name>routeBuilder-classes</param-name>
    	<param-value>
    		com.jeppesen.jcms.integrator.routes.FooRouteBuilder,
    		com.jeppesen.jcms.integrator.routes.BarRouteBuilder
    	</param-value>
    </context-param>


### XML DSL routes ###

Routes could also be defined in XML, using the Camel XML DSL syntax. You may choose to have all your routes in a single XML file or distribute them over several XML files.
The uesd files needs to be listed in the ``WEB-INF/web.xml`` file.:

    <!-- Build routes from XML files. -->
    <!-- classpath:routes is equivalent to files stored under src/main/resources/routes -->
    <context-param>
    	<param-name>routeBuilder-xml</param-name>
    	<param-value>
    		classpath:routes/routes.xml,
    		classpath:routes/more-routes.xml
    	</param-value>
    </context-param> 

Note that the files should be stored in the ``src/main/resources/routes`` directory of your project.

### Application server ###

The application do not require any configuration information from the application server. However when you build your own integrator application you might need to supply configuration depending on what Camel components you would use in your routes.

As an example, if you need to interact with the ``JCMS message broker``, you would likely use the ``wpf.cmsbroker`` component. This component requires configuration information, so read its [documentation](../../wpf.cmsbroker/README.md) for instructions. 



