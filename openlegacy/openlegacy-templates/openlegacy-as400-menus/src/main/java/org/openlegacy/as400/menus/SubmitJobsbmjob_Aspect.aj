// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.as400.menus;

import java.util.*;
import org.openlegacy.terminal.ScreenEntity;

privileged @SuppressWarnings("unused") aspect SubmitJobsbmjob_Aspect {

    declare parents: SubmitJobsbmjob implements ScreenEntity;
    private String SubmitJobsbmjob.focusField;
    
	

	

	

	

	

	

	

	

	

    

    public String SubmitJobsbmjob.getCommandToRun(){
    	return this.commandToRun;
    }
    
    public void SubmitJobsbmjob.setCommandToRun(String commandToRun){
    	this.commandToRun = commandToRun;
    }



    public String SubmitJobsbmjob.getJobDescription(){
    	return this.jobDescription;
    }
    
    public void SubmitJobsbmjob.setJobDescription(String jobDescription){
    	this.jobDescription = jobDescription;
    }



    public String SubmitJobsbmjob.getJobName(){
    	return this.jobName;
    }
    
    public void SubmitJobsbmjob.setJobName(String jobName){
    	this.jobName = jobName;
    }



    public String SubmitJobsbmjob.getJobPriorityonJobq(){
    	return this.jobPriorityonJobq;
    }
    
    public void SubmitJobsbmjob.setJobPriorityonJobq(String jobPriorityonJobq){
    	this.jobPriorityonJobq = jobPriorityonJobq;
    }



    public String SubmitJobsbmjob.getJobQueue(){
    	return this.jobQueue;
    }
    
    public void SubmitJobsbmjob.setJobQueue(String jobQueue){
    	this.jobQueue = jobQueue;
    }



    public String SubmitJobsbmjob.getLibrary(){
    	return this.library;
    }
    
    public void SubmitJobsbmjob.setLibrary(String library){
    	this.library = library;
    }



    public String SubmitJobsbmjob.getLibrary1(){
    	return this.library1;
    }
    
    public void SubmitJobsbmjob.setLibrary1(String library1){
    	this.library1 = library1;
    }



    public String SubmitJobsbmjob.getOutputPriorityonOutq(){
    	return this.outputPriorityonOutq;
    }
    
    public void SubmitJobsbmjob.setOutputPriorityonOutq(String outputPriorityonOutq){
    	this.outputPriorityonOutq = outputPriorityonOutq;
    }



    public String SubmitJobsbmjob.getPrintDevice(){
    	return this.printDevice;
    }
    
    public void SubmitJobsbmjob.setPrintDevice(String printDevice){
    	this.printDevice = printDevice;
    }




    public String SubmitJobsbmjob.getFocusField(){
    	return focusField;
    }
    public void SubmitJobsbmjob.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
}