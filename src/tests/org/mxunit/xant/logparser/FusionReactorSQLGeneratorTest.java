/**
 * 
 */
package tests.org.mxunit.xant.logparser;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.StringContains.containsString;

import org.apache.tools.ant.BuildFileTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mxunit.xant.logparser.FusionReactorLogParser;

/**
 * @author Billy
 *
 */
public class FusionReactorSQLGeneratorTest extends BuildFileTest {
	FusionReactorLogParser parser;
   
	
	@Test
	public void testNormalizeDirectory(){
		parser.setOutputDir("foo/bar//");
		System.out.println(parser.getOutputDir());
		String actual = parser.getOutputDir(); 
		assertThat(actual, is("foo/bar"));
	}
	
	
	@Test
	public void testThatGropuByGetsSet(){
		parser.setTempType("request");
		parser.setGroupBy("foo");
		parser.setSql();
		parser.configure("request");
		String sql = parser.getSql();
		System.out.println(parser.getSql());
		assertThat(sql,containsString("group by"));
	}
	
	
	@Test
	public void testThatColumnListGetsSet(){
		parser.setTempType("request");
		parser.setColumnList("foo,bar");
		parser.setSql();
		parser.configure("request");
		String sql = parser.getSql();
		System.out.println(parser.getSql());
		assertThat(sql,containsString("select top 1000 foo,bar"));
	}
	
	
	@Test
	public void testThatIdGetSetUsingConfigure(){
		parser.setId("myId");
		parser.setFileName(null);
		parser.setTempType("request");
		//parser.setSql();
		parser.configure("request");
		String sql = parser.getSql();
		System.out.println(parser.getSql());
		assertThat(sql,containsString("into xant-temp\\frantic-request-myId.html"));
	}
	
	
	@Test
	public void testThatIdGetSet(){
		parser.setId("myId");
		parser.setTempType("request");
		parser.setFileName("html");
		parser.setSql();
		String sql = parser.getSql();
		System.out.println(parser.getSql());
		assertThat(sql,containsString("into xant-temp\\frantic-request-myId.html"));
	}
	
	@Test
	public void testThatFilterGetSet(){
		parser.setFilter("date='1/1/2008' and foo='bar'");
		parser.setTempType("request");
		parser.setFileName("html");
		parser.setSql();
		String sql = parser.getSql();
		System.out.println(parser.getSql());
		assertThat(sql,containsString("and foo='bar"));
	}
	
	
	@Test
	public void testThatSortOrderGetSet(){
		parser.setSort("foo,bar");
		parser.setTempType("request");
		parser.setFileName("html");
		parser.setSql();
		String sql = parser.getSql();
		System.out.println(parser.getSql());
		assertThat(sql,containsString("order by"));
	}
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		parser = new FusionReactorLogParser();
		configureProject("build.xml");
		parser.setProject(this.getProject());
		
		parser.setLogDir("frlogs/");
		parser.setLog("request-0.log");
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

}
