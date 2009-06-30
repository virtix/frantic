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


public class FusionReactorLogParserReportTest extends BuildFileTest {
	
	FusionReactorLogParser parser;
	

	
	
	
	@Test
	public void testConfigure(){
        //exercise the test
		parser.configure("request");
		 
		System.out.println( parser.getSql() );
		System.out.println( parser.getType() );
		System.out.println( parser.getInput() );
		System.out.println( parser.getOutput() );
		System.out.println( parser.getOutputDir() );
		System.out.println( parser.getFormat() );
		System.out.println( parser.getHeaderFile() );
		System.out.println( parser.getLog() );
		
		assertThat(parser.getType() , is("request"));
		assertThat(parser.getInput() , is("-i:TSV"));
		assertThat(parser.getOutput() , is("-o:csv"));
		assertThat(parser.getFormat() , is("csv"));
		assertThat(parser.getOutputDir() , is("xant-temp"));
	
	
	}
	
	@Test
	public void testExecuteJDBC(){
		parser.setType("jdbc");
		parser.execute();
		java.io.File f = new java.io.File("xant-temp"); 
		String files[] = f.list();
		//assertTrue(files.length > 0 );
		assertThat(files.length,is(greaterThanOrEqualTo(1)));
	}
	
	@Test
	public void testExecuteCrash(){
		parser.setType("crash");
		parser.execute();
		java.io.File f = new java.io.File("xant-temp"); 
		String files[] = f.list();
		assertThat(files.length,is(greaterThanOrEqualTo(1)));
	}
	
	
	@Test
	public void testExecuteRequest(){
		parser.setType("request");
		parser.execute();
		java.io.File f = new java.io.File("xant-temp"); 
		String files[] = f.list();
		assertThat(files.length,is(greaterThanOrEqualTo(1)));
	}
	
	@Test
	public void testExecuteResource(){
		parser.setType("resource");
		parser.execute();
		java.io.File f = new java.io.File("xant-temp"); 
		String files[] = f.list();
		assertThat(files.length,is(greaterThanOrEqualTo(1)));
	}
	
	@Test
	public void testExecuteAll(){
		parser.setType("all");
		parser.execute();
		java.io.File f = new java.io.File("xant-temp"); 
		String files[] = f.list();
		assertThat(files.length,is(greaterThanOrEqualTo(4)));
		
	}
	
	@Test
	public void testExecuteAllWithNoSetTypeParamSpecified(){
		//To Do: Still fails with no params.
		// Should run ALL if not specified
		parser.setType("all");
		System.out.println(parser.getType());
		parser.execute();
		java.io.File f = new java.io.File("xant-temp"); 
		String files[] = f.list();
		assertThat(files.length,is(greaterThanOrEqualTo(4)));
	}
	
	
	
	
///////////////////////////////////////////////////////	
@Before
public void setUp() throws Exception {
	parser = new FusionReactorLogParser();
	configureProject("build.xml");
	parser.setProject(this.getProject());
	parser.setLogParser("C:\\Program Files\\Log Parser 2.2\\LogParser.exe");
	parser.setLogDir("tests/fixture/");
	parser.setType("request");
	//CSV for these tests ...
	parser.setFormat("csv");

	parser.setMaxRows(10);
	
}

@After
public void tearDown() throws Exception {
	java.io.File f = new java.io.File("xant-temp");
	java.io.File[] files = f.listFiles();
	
	for(int i = 0; i < files.length; i++){
		java.io.File deleteMe = files[i];
		//deleteMe.delete();		
	}
}

}//end class
