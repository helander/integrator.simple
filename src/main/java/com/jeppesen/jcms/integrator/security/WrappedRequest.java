package com.jeppesen.jcms.integrator.security;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.Collections;

public class WrappedRequest extends HttpServletRequestWrapper{

	private Map headerMap;

	public WrappedRequest(HttpServletRequest request){
		super(request);
		headerMap = new HashMap();
	}

	public void addHeader(String name, String value){
		headerMap.put(name, new String(value));
	}
	
	public Enumeration getHeaderNames(){
		HttpServletRequest request = (HttpServletRequest)getRequest();
		List list = new ArrayList();
		for( Enumeration e = request.getHeaderNames() ;  e.hasMoreElements() ; ) 
			list.add(e.nextElement().toString());
		for( Iterator i = headerMap.keySet().iterator() ; i.hasNext() ; ){
			list.add(i.next());
		}
		return Collections.enumeration(list);
	}

	public String getHeader(String name){
		Object value;
		if((value = headerMap.get(name)) != null)
			return value.toString();
		else
			return ((HttpServletRequest)getRequest()).getHeader(name);
	}
}