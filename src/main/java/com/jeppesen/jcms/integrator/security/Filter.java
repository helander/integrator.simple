package com.jeppesen.jcms.integrator.security;

import java.io.IOException;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.jeppesen.jcms.wpf.security.cacs.config.Config;
import com.jeppesen.jcms.wpf.security.cacs.UserData;
import com.jeppesen.jcms.wpf.servicecontext.ServiceContext;
import com.jeppesen.jcms.wpf.servicecontext.DataContextState;

@WebFilter(filterName = "Integrator.Filter", urlPatterns = { "/integrator/*" })
public class Filter implements javax.servlet.Filter {

    @Inject
    UserData userData;

    private FilterConfig config;

    Logger logger = LoggerFactory.getLogger(Filter.class);

    public Filter() {
    }

    @Override
    public void destroy() {
        logger.info("Integrator Filter : [" + config.getFilterName() + "] destroyed");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        logger.info("Integrator Filter : [" + config.getFilterName() + "] doFilter");

        HttpServletRequest req = (HttpServletRequest) request;

		if (userData.getUserId() == null) {
			logger.info("UserData.userId == null");
			if (req.getUserPrincipal() != null) {
				userData.setUserId(req.getUserPrincipal().getName());				
				userData.setUserRole("ROOT");	//FIX			
			}
			logger.info("UserData.userId = "+userData.getUserId());
			logger.info("UserData.role = "+userData.getUserRole());
		}



		WrappedRequest modifiedRequest = new WrappedRequest(req); 


		DataContextState contextState = userData.getDataContextState();
		if (contextState == null) {
			contextState = new DataContextState();
			contextState.setState(null);
			userData.setDataContextState(contextState);
		}
		synchronized(contextState) {
			if (contextState.getState() != null)
				modifiedRequest.addHeader("jcms_state", contextState.getState());	
		}
		modifiedRequest.addHeader("jcms_role", userData.getUserRole());	
		modifiedRequest.addHeader("jcms_client", userData.getUserId());	
		Map<String,List<String>> attributes = userData.getUserAttributes();
		if (attributes != null) {
			modifiedRequest.addHeader("jcms_attributes", attributes.toString());	
		}			
		
        chain.doFilter(modifiedRequest, response);
		
		HttpServletResponse rsp = (HttpServletResponse)response;
		
		String receivedState = rsp.getHeader("jcms_state");
		if (receivedState != null)
			synchronized(contextState) {
				contextState.setState(receivedState);	
				logger.info("Integrator Filter : [" + config.getFilterName() + "] Service context state updated to "+receivedState);
			}
			
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        logger.info("Integrator Filter : [" + config.getFilterName() + "] created.");
    }

}
