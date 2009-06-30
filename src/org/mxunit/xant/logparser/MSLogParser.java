/**
 * 
 */
package org.mxunit.xant.logparser;


import java.io.IOException;
import java.util.Vector;
import java.util.Iterator;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Main;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Execute;
import org.apache.tools.ant.taskdefs.LogStreamHandler;
import org.apache.tools.ant.types.Commandline;

/**
 * @author bill|y shelton
 *
 */
public class MSLogParser extends Task {

	//Most command-line options ...
	private String checkPoint = null;
	private String compact = null;
	private String conversion = null;
	private String e = null;
	private String fieldName = null;
	private String file = null;
	private String fileMode = null;
	private String headerFile = null;
	private String headers = null;
	private String ignoreWarnings = null;
	private String input = null;
	private String iw = null;
	private String logParser = null;
	private String maxErrors = null;
	private String multiSite = null;	
	private String noEmptyField = null;
	private String output = null;
	private String oDQuotes = null;
	private String queryInfo = null;
	private String quotes = null;
	private String quiet = "ON"; //make sure not to prompt ant to continue
    private String restoreDefaults = null;  
   	private String rootName = null;
    private String rowName = null; 
    private String rowsToProcess =  null;
	private String saveDefaults = null;
	private String schemaType  = null;
	private String separator = null;
	protected Vector sqlCommands = new Vector();
	private String standAlone  = null;
	private String stats = null;
	private String structure = null;
	private String template = null; 
	private String tpl = null;
	private String xslLink = null;
	
	//Represents nested text in <sql> ...
	private String sql = null;
	 
	
	public SQLCommand createSQL(){
	  SQLCommand sql = new SQLCommand();
	  sqlCommands.add(sql);
	  return sql;
	 }
	
	
	
/**
 * Main executed by calling logparser task
 * @author bill|y
 * @exception BuildException
 * 
 * */	
	 public void execute() throws BuildException {
		
		log("Hello, MSLogParser.");
		
		if(this.getLogParser() == null || this.getLogParser().equals("")){
	 		 throw new BuildException("LogParser executable must be specified in *logparser* attribute.");
	 	 }
		int results = -1;
		
		//To Do: Set the loglevel to verbose programatically
		//this.getDefaultLogger().setMessageOutputLevel(Project.MSG_VERBOSE);
		
	
		for(Iterator i = sqlCommands.iterator();i.hasNext();){
	      SQLCommand sqlCmd = (SQLCommand)i.next();
	      this.sql = sqlCmd.getMessage();
	      Commandline cmd = new LogParserCommandLine(this);	
		  results = run(cmd);
		  log("LogParser return-code: " + Integer.toString(results));
	    }
		
		
	}

	
/**
 * Runs the commandline and returns the code from the 
 * execution.
 * 
 * @return int
 * 
 * */	
	
 protected int run(Commandline cmd) {
   try {
     Execute exe = new Execute(new LogStreamHandler(this,
                                    Project.MSG_VERBOSE,
 		                            Project.MSG_WARN));
 	  exe.setAntRun(getProject());
 	  exe.setWorkingDirectory(getProject().getBaseDir());
 	  exe.setCommandline(cmd.getCommandline());
 	  // Use the OS launcher so we get environment variables
 	  exe.setVMLauncher(false);
 	  return exe.execute();
 	} catch (IOException e) {
 	    throw new BuildException(e, getLocation());
   }
 }

 
	 

	 
	 /**********************************************************
	  * 
	  * 
	  *    Attribute Setter/Getters        
	  * 
	  * 
	  ***********************************************************/
	 
      
 
 
 
 
	   public String getC() {
		return (conversion != null) ? "-c" : "";
	   }
			
		public String getSql() {
		  return  this.sql;
	   }

		public String getCheckPoint() {
			return (checkPoint != null) ? "-iCheckPoint:" + checkPoint : "";
		}
	
	   public String getCompact() {
			return (compact != null) ? "-compact:" + compact : "";
		}
	
	
		public String getConversion() {
			return (conversion != null) ? "-c" : "";
		}
	
	
		public String getE() {
			return (e != null) ? "-e:" + e : "";
		}
	
	
		public String getFieldName() {
			return (fieldName != null) ? "-fieldname:" + fieldName : "";
		}
	
	
		public String getFile(){
			return (this.file != null) ? "file:" + this.file : "";
		}
		
		public String getFileMode(){
			return (this.fileMode != null) ? "-fileMode:" + this.fileMode : "";
		}
	
		public String getHeaders(){
			return (this.headers != null) ? "-headers:" + this.headers : "";
		}
		public String getHeaderFile(){
			return (this.headerFile != null) ? "-iHeaderFile:" + this.headerFile : "";
		}
	
	
		public String getIgnoreWarnings() {
			return (ignoreWarnings != null) ? "-iw:" + ignoreWarnings : "";
		}
	
	
		public String getInput() {
			return (this.input != null) ? "-i:"+this.input : "";
		}
	
	
		public String getIw() {
			return (iw != null) ? "-iw:" + iw : "";
		}
	
	
		public String getLogParser(){
		  if (this.logParser == null) {
		    throw new BuildException("logParser attribute must be set to the path to your executable!", getLocation());
		   }
			return  this.logParser;
		}
	
	
		public String getMaxErrors() {
			return (maxErrors != null) ? "-e:" + maxErrors : "";
		}
	
	
		public String getMultiSite() {
			return (multiSite != null) ? "-multiSite:" + multiSite : "";
		}
		
		public String getNoEmptyField() {
			return (noEmptyField != null) ? "-noEmptyField:" + noEmptyField : "";
		}

			
		public String getODQuotes() {
			return (oDQuotes!= null) ? "-oDQuotes:" + oDQuotes : "";
		}


		public String getQuotes() {
			return (quotes!= null) ? "-oDQuotes:" + quotes : "";
		}


		public String getOutput(){
			return (this.output != null) ? "-o:" + this.output : "";
		}
	
	
		public String getQueryInfo() {
			return (queryInfo != null) ? "-queryInfo" : "";
		}
	
	
		public String getQuiet() {
			return (quiet != null) ? "-q:" + quiet : "";
		}
	
	
		public String getRestoreDefaults() {
			return (restoreDefaults != null) ? "-restoreDefaults:" + restoreDefaults : "";
		}
	
	
		public String getRootName() {
		  return (rootName != null) ? "-rootname:" + rootName : "";
		}
	
	
		public String getRowName() {
			return (rowName != null) ? "-rowname:" + rowName : "";
		}
	
	
		public String getRowsToProcess() {
		  return (rowsToProcess != null) ? "-rtp:" + rowsToProcess : "";
		 }
	
	
		public String getSaveDefaults() {
			return (saveDefaults != null) ? "-saveDefaults:" + saveDefaults : "";
		}
	
	
		public String getSchemaType() {
			return (schemaType != null) ? "-schemaType:" + schemaType : "";
		}
	
	
		public String getSeparator(){
			return (this.separator != null) ? "-iSeparator:" + this.separator : "";
		}
	
	
		public String getStandAlone() {
			return (standAlone != null) ? "-standAlone:" + standAlone : "";
		}
	
	
		public String getStats() {
			return (stats != null) ? "-stats:" + stats : "";
		}
	
	
		public String getStructure() {
			return (structure != null) ? "-structure:" + structure : "";
		   }
	
	
		public String getTemplate() {
			return (template != null) ? "-tpl:" + template : "";
		}
	
	
		public String getTpl() {
			return (tpl != null) ? "-tpl:" + tpl : "";
		}
	
	
		public String getXslLink() {
			return (xslLink != null) ? "-xslLink:" + xslLink : "";
		}


		public void setC(String c) {
			this.conversion = c;
		}
	
		public void setCheckPoint(String checkPoint) {
			this.checkPoint = checkPoint;
		}
		
		public void setCompact(String compact) {
			this.compact = compact;
		}
	
	
		public void setConversion(String conversion) {
			this.conversion = conversion;
		}
	
	
		public void setE(String e) {
			this.e = e;
		}
	
	
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
	
	
		public void setFile(String file){
			this.file = file;
		}
		
		public void setFileMode(String fileMode){
			this.fileMode = fileMode;
		}
	
	
		public void setHeaderFile(String headerFile){
			this.headerFile = headerFile;
		}
		public void setHeaders(String headers){
			this.headers = headers;
		}
	
		public void setIgnoreWarnings(String ignoreWarnings) {
			this.ignoreWarnings = ignoreWarnings;
		}
	
	
		public void setInput(String input) {
			this.input = input;
		}
	
	
		public void setIw(String iw) {
			this.iw = iw;
		}
	
		public void setLogParser(String logParser){
			this.logParser = logParser;
		}
	
	
		public void setMaxErrors(String maxErrors) {
			this.maxErrors = maxErrors;
		}
	
	
		public void setMultiSite(String multiSite) {
			this.multiSite = multiSite;
		}
	
	
		public void setNoEmptyField(String noEmptyField) {
			this.noEmptyField = noEmptyField;
		}
			
		public void setODQuotes(String quotes) {
			oDQuotes = quotes;
		}
		
		public void setOutput(String output){
			this.output = output;
		}
	
	
		public void setQueryInfo(String queryInfo) {
			this.queryInfo = queryInfo;
		}
	
	
		public void setQuiet(String quiet) {
			this.quiet = quiet;
		}
		  
		public void setQuotes(String quotes) {
		   this.quotes = quotes;
		}  
		
		public void setRestoreDefaults(String restoreDefaults) {
			this.restoreDefaults = restoreDefaults;
		}

		public void setRootName(String rootName) {
		  this.rootName = rootName;
		}
				
		public void setRowName(String rowName) {
			this.rowName = rowName;
		}
		
		public void setRowsToProcess(String rowsToProcess) {
			this.rowsToProcess = rowsToProcess;
	    }
			
		public void setSaveDefaults(String saveDefaults) {
			this.saveDefaults = saveDefaults;
		}
		public void setSchemaType(String schemaType) {
			this.schemaType = schemaType;
		}
		
		 public void setSql(String sql) {
			  this.sql = sql;
		}
		
		public void setSeparator(String separator){
				this.separator = separator;
		}
		
		public void setStandAlone(String standAlone) {
			this.standAlone = standAlone;
		}
		
		public void setStats(String stats) {
			this.stats = stats;
		}
		public void setStructure(String structure) {
			 this.structure = structure;
		   }
			 
		public void setTemplate(String template) {
			this.template = template;
		}

		public void setTpl(String tpl) {
			this.tpl = tpl;
		} 
		
		public void setXslLink(String xslLink) {
			this.xslLink = xslLink;
		}

		
	 
	 /**********************************************************
	  * 
	  * 
	  *    End Setter/Getters           
	  * 
	  * 
	  ***********************************************************/
	 
			
	




		
		/**********************************************************
		 * 
		 * 
		 *    Inner class to handle SQL statements.         
		 * 
		 * 
		 ***********************************************************/	 
			 
		
			public class SQLCommand {
			  //need to get the content betweem <sql> tags</sql>
			  private String message = null;
			 public SQLCommand(){} //constructor
			  public void addText(String text) {
			     message = text;
			  }	
			  
			  public String getMessage(){
				  this.message = this.message.replaceAll("\n", "");
				  return message.trim();
			  }
				 
			}



 
	/**********************************************************
	 * 
	 * 
	 *    End SQLCommand Inner class         
	 * 
	 * 
	 ***********************************************************/	 	 
	
} //end MSLogParser
