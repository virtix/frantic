package tests.org.mxunit.xant.logparser;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.StringContains.containsString;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import java.io.File;
import org.mxunit.xant.logparser.XMLUtil;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.types.Resource;

import org.apache.tools.ant.BuildFileTest;

public class XMLUtilTest extends BuildFileTest {
  
	public XMLUtil xmlUtil;
	File in , out, stylesheet, jdbcin,jdbcout;
	File requestin , requestout, resourcein, resourceout, crashin, crashout;
	FileResource xsl;
	Resource res;
	
	
	
	@Test
	public void testGetStyleSheetAsResourceAndTransFormJDBCLog() throws Exception {
		res = xmlUtil.getStylesheet("frantic.xsl");
		System.out.println(res.toString());
		xmlUtil.traxform(jdbcin,jdbcout,res);
		assertTrue(jdbcout.exists());
	}
	
	@Test
	public void testGetStyleSheetAsResourceAndTransFormCrashLog() throws Exception {
		res = xmlUtil.getStylesheet("frantic.xsl");
		System.out.println(res.toString());
		xmlUtil.traxform(crashin,crashout,res);
		assertTrue(crashout.exists());
	}
	
	@Test
	public void testGetStyleSheetAsResourceAndTransFormResourceLog() throws Exception {
		res = xmlUtil.getStylesheet("frantic.xsl");
		System.out.println(res.toString());
		xmlUtil.traxform(resourcein,resourceout,res);
		assertTrue(resourceout.exists());
	}
	
	@Test
	public void testGetStyleSheetAsResourceAndTransFormRequestLog() throws Exception {
		res = xmlUtil.getStylesheet("frantic.xsl");
		System.out.println(res.toString());
		xmlUtil.traxform(requestin,requestout,res);
		assertTrue(requestout.exists());
	}
	
	
	
	@Before
	public void setUp(){
		xmlUtil = new XMLUtil();
		//configureProject("build.xml");
		 out = new File("C:/Documents and Settings/billy/workspace/xant/src/xml/out/frantic-request-new.html");
		 in = new File("C:/Documents and Settings/billy/workspace/xant/src/xml/data/frantic-request.xml");
		 
		 jdbcout = new File("C:/Documents and Settings/billy/workspace/xant/src/xml/out/frantic-jdbc.html");
	     jdbcin = new File("C:/Documents and Settings/billy/workspace/xant/src/xml/data/frantic-jdbc.xml");
	     
	     requestin = new File("C:/Documents and Settings/billy/workspace/xant/src/xml/data/frantic-request.xml");
	     requestout = new File("C:/Documents and Settings/billy/workspace/xant/src/xml/out/frantic-request.html"); 
	     resourcein = new File("C:/Documents and Settings/billy/workspace/xant/src/xml/data/frantic-resource.xml"); 
	     resourceout = new File("C:/Documents and Settings/billy/workspace/xant/src/xml/out/frantic-resource.html");
	     crashin = new File("C:/Documents and Settings/billy/workspace/xant/src/xml/data/frantic-crash.xml"); 
	     crashout = new File("C:/Documents and Settings/billy/workspace/xant/src/xml/out/frantic-crash.html");

	     stylesheet = new File("C:/Documents and Settings/billy/workspace/xant/src/xml/xsl/frantic-request.xsl");
	     xsl = new FileResource();
	     xsl.setFile(stylesheet);
		
	}
	
	@After
	public void tearDown(){
		
		
	}

}
