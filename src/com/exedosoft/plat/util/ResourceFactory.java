package com.exedosoft.plat.util;

import javax.naming.*;

/**
 * Looks up resource defined in the web.xml file<br>
 */
public class ResourceFactory{

  private static Context env;
  private static  ResourceFactory rf ;
  private ResourceFactory(){
    try {
      env = (Context)(new InitialContext()).lookup("java:comp/env");
    }
    catch (NamingException ex) {

    }
  }
/**
 *
 * Looks up resource defined in the web.xml file<br>
 * Gets the object
 */
  public  static Object getResource(String resourceName){

     if(rf==null){
       rf = new ResourceFactory();
     }
    try{
      return env.lookup(resourceName);
    
    }
    catch(Exception e){
      e.printStackTrace();
      System.out.println("Env is Null,but return 'conf'");
      return "conf";
    }finally{
    	try {
			env.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
  }
}
