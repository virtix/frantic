package org.mxunit.xant.logparser;


/* 4.29.08
 * To Do: Normalize Directory paths .. address trailing slashes.
 * 
 * 
 * */


import java.util.StringTokenizer;
import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.util.FileUtils;
import java.text.DateFormat;
import java.util.Calendar;



public class FusionReactorLogParser extends MSLogParser {
  
	public static int ALL_LOGS = 5;
	public static String CRASH_HEADERS = "crashprotection-headers.txt";
	public static int CRASH_LOG = 2;
	public static String CRASH_LOGS = "crashprotection-*.log";
	public static int CSV_TYPE = 2;
	private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
	public static int HTML_TYPE = 1;
	public static String JDBC_HEADERS = "jdbc-headers.txt";
	public static int JDBC_LOG = 3;
	public static String JDBC_LOGS = "jdbc-*.log";
	public static String REQUEST_HEADERS = "request-headers.txt";
	public static int REQUEST_LOG = 1;
	public static String REQUEST_LOGS = "request-*.log";
	public static String RESOURCE_HEADERS = "resource-headers.txt";
	public static int RESOURCE_LOG = 4;
	public static String RESOURCE_LOGS = "resource-*.log";
	
	//what to select from
	private String columnList = null; 
	//ASC/DESC flag
	private String direction = "desc";
	//file name for labeling generated reports
	private String fileName = null;
	//for where clause. Format: foo=bar and this=that or bar=fu
	private String filter = null;
	//html,csv,
	private String format = "html"; 
	private String groupBy = null;
	//alternate location of header files
	private String headerFileLocation = null;
	//the unique identifier for the run
	private String id = null; 
	private String log = null;
	private String logDir = null;	
	private int maxRows = 1000;
	//Where to store output
	private String outputDir = "xant-temp"; 
	private String sort = "date,time";
	private String sql = ""; 
	private String tempType = null;
	private String title = null;
	//type of report: request,jdbc,crash ...
	private String type = null;
	private String xsl = null;
	private XMLUtil xmlUtil = new XMLUtil();
	private String xmlFileName = null;

  
/**
 * 
 * Configures the commandLine for this class
 * 
 * @param java.lang.String
 * 
 * */
public void configure(String type){
	
	setTempType(type);
		
	if(type.equals("request")){
		setHeaderFile(getLogDir() + REQUEST_HEADERS);
		setLog(REQUEST_LOGS);
	}
	else if(type.equals("crash")){
		setHeaderFile(getLogDir() + CRASH_HEADERS);
		setLog(CRASH_LOGS);
	}
	else if(type.equals("jdbc")){
		setHeaderFile(getLogDir() + JDBC_HEADERS);
		setLog(JDBC_LOGS);
	}
	else if(type.equals("resource")){
		setHeaderFile(getLogDir() + RESOURCE_HEADERS);
		setLog(RESOURCE_LOGS);
	}
	else{
		throw new RuntimeException("Unknown log type: " + type);
	}
	
	setFileName(type);
	
	if(getFormat().equals("csv")){
		setOutput("csv");
	}
	else{ //this will be either xml or html, so we need xml. Someday, chart ...
		setOutput("xml");
		/*
		 *schemaType="0"
		 * */
		setRootName("frantic");
		setRowName("entry");
		setCompact("ON");
		setSchemaType("0");
	}
	
	//Applies to all types
	setInput("TSV");
	setSeparator("space");
	setSql();
}	


/**
 * 
 * Run by ant when invoked.
 * 
 * */
public void execute() throws BuildException {
	log("Hello, FusionReactorLogParser.");
	log("Outputting results to: " + getOutputDir());
	
	
	if(type == null || type.equals("all")){
		setType("request,jdbc,crash,resource");
	}
	
	StringTokenizer types = new StringTokenizer(getType());
	
	//log("type=" + type);
	//log("Number of nested <sql> elements: " + sqlCommands.size());
	
	//Maybe won't need this ... but maybe good for quick/dirty queries?
	//Run this only if nested sql elements are present.
    if(sqlCommands.size() > 0){
	  	MSLogParser.SQLCommand sqlCmd = new  MSLogParser.SQLCommand();
	   	sqlCmd = (MSLogParser.SQLCommand)sqlCommands.elementAt(0);
	   	setInput("TSV");
		setSeparator("space");
	   	//this.sqlCommands = sqlCommands.elementAt(1).
	   	 //SQLCommand sqlCmd = (SQLCommand)i.next();
	     this.sql = sqlCmd.getMessage();
	     Commandline cmd = new LogParserCommandLine(this);	
	     log("Running cmd: " + cmd.toString());
		 int results = super.run(cmd);
		 return;
	  }
	      
	
    /*
     * 04.25.08
     * To Do: If the type is html, then transform the xml
     * */
	while(types.hasMoreTokens()){
		String token = types.nextToken(",");
		configure(token);     
	    Commandline cmd = new LogParserCommandLine(this);	
	    log("Running cmd: " + cmd.toString());
	    System.out.println("Running cmd: " + cmd.toString());
	    int results = super.run(cmd);
	    System.out.println(getOutput());
	    //assuming LogParser generated XML
	    //how do we know we want html? what's the rule
	    if(getFormat().equals("html")){
	    	try{
	    	transform();
	    	}
	    	catch (Exception be){
	    		throw new BuildException(be);
	    	}
	    }
	    //
	}
}






/*****************************************************************
 * 
 * 
 * 					Setters and Getters
 * 
 * 
 * **************************************************************/


public String getColumnList() {
	return (columnList != null ? columnList : "*");
}

public String getDirection() {
	return direction;
}

public String getFileName(){
	return this.fileName;
}

public String getFilter() {
	return (filter != null ? "where " + filter : "");
}

public String getFormat() {
	return this.format;
}

public String getGroupBy(){
	return (this.groupBy != null ? " group by " + groupBy : "");
}

public String getHeaderFileLocation(){
	return this.headerFileLocation;
}
public String getId() {
	return (id != null ? id : java.util.UUID.randomUUID().toString());
}

public String getLog(){
	return this.log;
}

public String getLogDir() {
	return this.logDir;
}

public int getMaxRows() {
	return this.maxRows;
}


public String getOutputDir () {
	
	//To Do: reg
	if(outputDir != null){
		//strip trailing slash
		String slash = "/|\\$";
		//slash = "/|\\$";
		//char last = outputDir.charAt(outputDir.length()-1);
		//old: was working on 4/28/08 but smelly
		//if(outputDir.endsWith(slash)){
		///	this.outputDir = outputDir.substring(0, outputDir.length()-2);	
		//}
		this.outputDir = this.outputDir.replace(slash, "");
	}
	
	return  this.outputDir;
}


/**
 * 
 * Specifies which columns are used to sort the query.
 * Default is <em>date,time</em>
 * 
 * 
 * */
public String getSort() {
	//To do: build so no sorting is required.
	//Case: If someone wants rows w/no date time info.
	return (sort != null ? " order by " + this.sort : "order by date,time");
}

public String getSql(){
	return this.sql;
}

public String getTempType() {
	return tempType;
}

public String getTitle() {
	return (title != null ? title : "FusionReactor Log Report");
}

public String getType() {
	return (this.type == null ? "all" : this.type);
}

public String getXSL(){
	return  this.xsl;
}


/******************************
 * 
 *          Setters
 * 
 * ****************************/

public void setColumnList(String columnList) {
	this.columnList = columnList;
}

/**
 * 
 * Adds sort direction to sql query.
 * 
 * */

public void setDirection(String direction) {
	this.direction = direction;
}


public void setFileName(String type){
	String fileName = "frantic-";
	fileName += getTempType() + "-";
	fileName += getId();
	fileName += "." + getFormat();
	this.fileName = fileName;
}

/**
 * 
 * Appends filter expression to where clause in sql query.
 * Default is an empty string;
 * 
 * */

public void setFilter(String filter) {
	this.filter = filter;
}

/**
 * One of HTML, CSV, or XML.
 * 
 * */
public void setFormat(String format) {
	log(format);
	if( ! format.equals("csv") && ! format.equals("html") && ! format.equals("xml")){
		throw new RuntimeException("Format: " + format + ", is not currently supported.");
	}
	this.format = format;
}


public void setGroupBy(String groupBy){
	this.groupBy = groupBy;
}


/**
 * 
 * Sets the alternative location from which to read the header information.
 * 
 * 
 * */
public void setHeaderFileLocation(String headerFileLocation){
	 this.headerFileLocation = headerFileLocation;
}

public void setId(String id) {
	this.id = id;
}

/**
 * 
 * Sets the log from which to read.
 * 
 * 
 * */
public void setLog(String log){
	 this.log = log;
}

/**
 * Used to get the location of log and header files
 * 
 * */
public void setLogDir(String logDir) {
	this.logDir = logDir;
}

/**
 * 
 * How many rows to fetch from the query. Default is 1000.
 * 
 * */
public void setMaxRows(int maxRows) {
	if(maxRows < 1){
		throw new RuntimeException("maxRows must be greater than 1. You entered " + maxRows);
	}
	this.maxRows = maxRows;
}

/**
 * Where the output files should be sent to.
 * 
 * */
public void setOutputDir (String outputDir) {
	 this.outputDir = outputDir;
}


/**
 * Inject project for testing.
 * 
 * */ 
public void setProject(Project project){
  this.project = project;	
}
public void setSort(String sort) {
	this.sort = sort;
}


/**
 * 
 * Default SQL statement. May be overridden by nested <sql> elements.
 * 
 * */
public void setSql(){
	String logDir = getLogDir();
	String outDir = getOutputDir();
	String sql = "";
	sql += "select top " + this.getMaxRows() + " " + getColumnList() + " ";
	sql += "into " + outDir + System.getProperty("file.separator") + setXmlFileName(getFileName()) + " ";
	sql += "from " + logDir +getLog() + " ";
	sql += " " + getFilter() + " ";
	sql += getSort();
	sql += " " + getDirection();
	sql += " " + getGroupBy();
		
	this.sql = sql ;
}


public void setTempType(String tempType) {
	this.tempType = tempType;
}






public void setTitle(String title) {
	this.title = title;
}


/**
 * Used to determine what kind of data to fetch:
 * One of request,jdbc,crashprotection, etc..
 * 
 * */
public void setType(String type) {
	this.type = type;
}

public void setXSL(String xsl){
 this.xsl = (xsl == null ? getClass().getResource("xsl/cf-app.xsl").toString() : xsl);
}

//Replaces .html w/.xml for output
public String setXmlFileName(String fileName){
	return fileName.replaceAll("\\.html$", ".xml");
}

/**
 * 
 * Wraps XMUtil.transorm(String, String)
 * 
 * */
public void transform() throws Exception {
	System.out.print("transforming ...");
	Resource resource = xmlUtil.getStylesheet("frantic.xsl");
	File out = new File(getOutputDir() + System.getProperty("file.separator") + getFileName());
	File in = new File(getOutputDir() + System.getProperty("file.separator") + setXmlFileName(getFileName()));
	log(in.toString());
	log(out.toString());
	try{
	   //Pass the title string to the Trax processor. 
	   //Displayed  in title and header of result doc
	   Calendar calendar = Calendar.getInstance();
	   String today = DateFormat.getDateInstance().format(calendar.getTime());
	   String time = DateFormat.getTimeInstance().format(calendar.getTime());
	   xmlUtil.setParam("title", getTitle());
	   xmlUtil.setParam("dateTimeString", today + " " + time);
	   xmlUtil.traxform(in, out, resource);
	}
	catch(Exception e){
	 throw new BuildException("XML Transformation failed for " + in.toString(), e);
	}
}

/****************************** End FusionReactorLogParser **********************************/
} //
