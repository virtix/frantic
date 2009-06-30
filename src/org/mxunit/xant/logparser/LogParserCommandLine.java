package org.mxunit.xant.logparser;


import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.BuildException;


public class LogParserCommandLine extends Commandline {


 public LogParserCommandLine(MSLogParser logParser) throws BuildException{

 	  
  this.setExecutable(logParser.getLogParser());

  this.createArgument().setValue(logParser.getC());
  this.createArgument().setValue(logParser.getSql());
  this.createArgument().setValue(logParser.getCompact());
  this.createArgument().setValue(logParser.getCheckPoint());
  this.createArgument().setValue(logParser.getConversion());
  this.createArgument().setValue(logParser.getE());
  this.createArgument().setValue(logParser.getFieldName());
  this.createArgument().setValue(logParser.getFile());
  this.createArgument().setValue(logParser.getFileMode());
  this.createArgument().setValue(logParser.getHeaderFile());
  this.createArgument().setValue(logParser.getHeaders());
  this.createArgument().setValue(logParser.getIgnoreWarnings());
  this.createArgument().setValue(logParser.getInput());
  this.createArgument().setValue(logParser.getIw());
  this.createArgument().setValue(logParser.getMaxErrors());
  this.createArgument().setValue(logParser.getMultiSite());
  this.createArgument().setValue(logParser.getNoEmptyField());
  this.createArgument().setValue(logParser.getOutput());
  this.createArgument().setValue(logParser.getODQuotes());
  this.createArgument().setValue(logParser.getQueryInfo());
  this.createArgument().setValue(logParser.getQuiet());
  this.createArgument().setValue(logParser.getQuotes());
  this.createArgument().setValue(logParser.getRestoreDefaults());
  this.createArgument().setValue(logParser.getRootName());
  this.createArgument().setValue(logParser.getRowName());
  this.createArgument().setValue(logParser.getRowsToProcess());
  this.createArgument().setValue(logParser.getSaveDefaults());
  this.createArgument().setValue(logParser.getSchemaType());
  this.createArgument().setValue(logParser.getSeparator());
  this.createArgument().setValue(logParser.getStandAlone());
  this.createArgument().setValue(logParser.getStats());
  this.createArgument().setValue(logParser.getStructure());
  this.createArgument().setValue(logParser.getTemplate());
  this.createArgument().setValue(logParser.getTpl());
  this.createArgument().setValue(logParser.getXslLink());


 }



}