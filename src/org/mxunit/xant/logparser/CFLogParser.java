package org.mxunit.xant.logparser;

import java.net.URL;

import org.apache.tools.ant.BuildException;

public class CFLogParser extends MSLogParser {

	
	
public void execute() throws BuildException {
  log("Hello, CFLogParser.");
  //URL url = this.getClass().getResource("templates/top-1000-req-template.tpl");
  //log(url.toString());
  setInput("CSV");

  //setTemplate(url.toString());
   super.execute();
}	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CFLogParser lp = new CFLogParser();
		System.out.println("CFLogParser\n\n");
		URL url = lp.getClass().getResource("templates/top-1000-req-template.tpl");
		System.out.print(url);
		//jar:file:/C:/apache/apache-ant-1.7.0/lib/ant-junit.jar!/org/apache/tools/ant/taskdefs/optional/junit/xsl/junit-frames.xsl
  		//@see http://java.sun.com/j2se/1.4.2/docs/api/java/net/JarURLConnection.html
	}

}
