package org.mxunit.xant.logparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.w3c.dom.Document;
import org.apache.tools.ant.taskdefs.optional.TraXLiaison;

import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.resources.URLResource;

public class XMLUtil extends Task {

	 protected Task task;	
	 protected Document document;
	 TraXLiaison trax;
	
	 protected static DocumentBuilderFactory dbFactory;

     static {
	   dbFactory = DocumentBuilderFactory.newInstance();
     }	
   
   public XMLUtil(){
	  try{
	   this.trax = new TraXLiaison();
	  }
	  catch(Exception e){
        e.printStackTrace();
	  }
   }
   
   
   /**
    * Set the xml file to be processed. This is a helper if you want
    * to set the file directly. Much more for testing purposes.
    * @param xmlfile xml file to be processed
    * @throws BuildException if the document cannot be parsed.
    */
   protected Document parseXMLFile(File xmlfile) throws BuildException {
       try {
           DocumentBuilder builder = dbFactory.newDocumentBuilder();
           InputStream in = new FileInputStream(xmlfile);
           try {
        	  return builder.parse(in);
            } finally {
               in.close();
           }
       } catch (Exception e) {
           throw new BuildException("Error while parsing document: " + xmlfile, e);
       }
   }
   
   
   
   
   public void setDocument(File xml){
	   this.document = parseXMLFile(xml);
   }
	
   
   public Document getDocument(){
	   return this.document;
   }
	
	
   /**
    * Returns the stylesheet for the specified name from the jar.
    * 
    * */
   public Resource getStylesheet(String name) {
       
           URLResource stylesheet = new URLResource();
           URL stylesheetURL = getClass().getClassLoader().getResource(
                   "org/mxunit/xant/logparser/xsl/" + name );
           stylesheet.setURL(stylesheetURL);
           return stylesheet;
    }
   
   
   
   /**
    * 
    * Pass an xslt parameter to the Trax engine
    * 
    * */
   public void setParam(String name, String value){
	   this.trax.addParam(name, value);
   }
   
   /**
    * Transforms IN to OUT using STYLESHEET resource.
    * 
    * */
   public void traxform(File in, File out, Resource styleSheet) throws BuildException,Exception {
      
	   
	   this.trax.setStylesheet(styleSheet);
	   this.trax.transform(in,out); 
       
   } 
   
   /**
    * Transforms IN to OUT using STYLESHEET file.
    * 
    * */
   public void traxform(File in, File out, File styleSheet) throws BuildException,Exception {
	   this.trax.setStylesheet(styleSheet);
	   this.trax.transform(in,out);    
   }
   
   
   
   
   
  
   
   

} //end class
