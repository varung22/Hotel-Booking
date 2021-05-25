package com.hotel.myapp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * It is a Dispatcher servlet which initializes all servlets
 * @author Vidhusha
 * 
 *
 */


public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {  
    
  /**
 * It is  a class of Myapp Dispatcher Servlet
 * It returns all the roots and config classes
 */

@Override  
  protected Class<?>[] getRootConfigClasses() {  
      // TODO Auto-generated method stub  
      return null;  
  }  

  /**
 *It returns MyAppConfig class
 */
@Override  
  protected Class<?>[] getServletConfigClasses() {  
      return new Class[] { MyAppConfig.class };  
  }  

  /**
 *It maps the servlet
 */
@Override  
  protected String[] getServletMappings() {  
      return new String[] { "/" };  
  }  

    
}  