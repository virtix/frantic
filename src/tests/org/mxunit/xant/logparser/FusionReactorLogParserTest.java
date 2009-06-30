package tests.org.mxunit.xant.logparser;

import org.mxunit.xant.logparser.*;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.StringContains.containsString;

import org.apache.tools.ant.BuildFileTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FusionReactorLogParserTest extends BuildFileTest {
	
	FusionReactorLogParser parser;
	
	@Test
	public void testGetSQL(){
		//Need to set these props for this test
		//parser.setOutputDir("/foo/bar/");
		parser.setLogDir("C:\\Documents and Settings\\Billy\\workspace\\xant\\tests\\fixture\\");
		parser.setSql();
		String sql = parser.getSql();
		System.out.println(sql);
		assertThat(sql, containsString("select "));
	}
	
	@Test
	public void testThatExecuteGeneratesCSV(){
		//Need to set these props for this test
		//parser.setOutputDir("/foo/bar/");
		parser.setLogParser("J:\\Log Parser 2.2\\LogParser.exe");
		parser.setLogDir("tests/fixture/");
		parser.setSql();
		String sql = parser.getSql();
		parser.execute();
		
		 System.out.println(sql);
		//assertThat(sql, containsString("select top 1000 * "));
	}
	
	
	@Test
	public void testGetXSL(){
		parser.setXSL("my.css");
		String xsl = parser.getXSL();
		System.out.println(xsl);
		assertEquals("my.css",xsl);
		parser.setXSL(null);
		xsl = parser.getXSL();
		System.out.println(xsl);
		assertThat(xsl, containsString("org/mxunit/xant/logparser/xsl/cf-app.xsl"));
	}
	
	
	
	@Test
	public void testSetOutputDir(){
		 parser.setOutputDir("/foo/bar/dir");
		 String actual = parser.getOutputDir();
		 System.out.println("outoutDir=" + actual);
		 assertThat(actual, is(equalTo("/foo/bar/dir")));
	}
	
	public void testSetOutputDirNull(){
		 String actual = parser.getOutputDir();
		 System.out.println("outoutDir=" + actual);
		 assertThat(actual, is(equalTo("xant-temp")));
	}

	
	
	@Test
	public void testSetLogDir() {
		parser.setLogDir("/path/to/logs");
		String actual = parser.getLogDir();
		assertEquals("/path/to/logs",actual);
	}
	
	@Test
	public void testThatGetFormatReturnsHtml() {
		String actual = parser.getFormat();
		assertEquals("html",actual);
	}
	
	@Test
	public void testThatGetFormatReturnsCsv() {
		parser.setFormat("csv");
		String actual = parser.getFormat();
		assertEquals("csv",actual);
	}
	
	@Test
	public void testThatSetFormatSetsHtml() {
		parser.setFormat("html");
		String actual = parser.getFormat();
		assertEquals("html",actual);
	}
	
	@Test
	public void testThatGetFormatReturnsUnsupportedException() {
		try{
			parser.setFormat("asdasd");
		}
		catch(RuntimeException e){
			parser.log("setFormat() to bogus asasdasda");	
		}
		
	}
	
	@Test
	public void testThatGetMaxRowsReturns1000() {
		assertEquals(1000, parser.getMaxRows());
	}
	
	@Test
	public void testThatGetMaxRowsReturns50000() {
		parser.setMaxRows(50000);
		assertEquals(50000, parser.getMaxRows());
	}
	
	@Test
	public void testThatGetMaxRowsWithNegativeInputThrowsException() {
		try{
			parser.setMaxRows(-1);
		}
		catch(Throwable t){
		 parser.log("setMaxRows() to -1");	
		}
	}
	
	
	@Test
	public void testThatBigestIntDoesSomething() {
	  try{
			parser.setMaxRows(Integer.MAX_VALUE+1);
		}
		catch(Throwable t){
		 parser.log("setMaxRows() to Integer.MAX_VALUE+1");	
		}
	}
	
	@Test
	public void testThatGetTypeWorks() {
	  
		String type = parser.getType();
		assertEquals("all",type);
		 
	}
	
	@Test
	public void testThatSetTypeWorks() {
		parser.setType("request,jdbc");
		String type = parser.getType();
		assertEquals("request,jdbc",type);
		 
	}
	
	
	
///////////////////////////////////////////////////////	
@Before
public void setUp() throws Exception {
	parser = new FusionReactorLogParser();
	configureProject("build.xml");
	parser.setProject(this.getProject());
	
}

@After
public void tearDown() throws Exception {}	
	
	
}
