// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.as400.menus;

import java.util.*;
import org.openlegacy.terminal.ScreenEntity;

privileged @SuppressWarnings("unused") aspect CopyFromPcDocumentcpyfrmpcd_Aspect {

    declare parents: CopyFromPcDocumentcpyfrmpcd implements ScreenEntity;
    private String CopyFromPcDocumentcpyfrmpcd.focusField;
    
	

	

	

	

	

	

	

	

	

	

	

    

    public String CopyFromPcDocumentcpyfrmpcd.getDbcsCodePage(){
    	return this.dbcsCodePage;
    }
    
    public void CopyFromPcDocumentcpyfrmpcd.setDbcsCodePage(String dbcsCodePage){
    	this.dbcsCodePage = dbcsCodePage;
    }



    public String CopyFromPcDocumentcpyfrmpcd.getFormatOfPcData(){
    	return this.formatOfPcData;
    }
    
    public void CopyFromPcDocumentcpyfrmpcd.setFormatOfPcData(String formatOfPcData){
    	this.formatOfPcData = formatOfPcData;
    }



    public String CopyFromPcDocumentcpyfrmpcd.getFromDocument(){
    	return this.fromDocument;
    }
    
    public void CopyFromPcDocumentcpyfrmpcd.setFromDocument(String fromDocument){
    	this.fromDocument = fromDocument;
    }



    public String CopyFromPcDocumentcpyfrmpcd.getFromFolder(){
    	return this.fromFolder;
    }
    
    public void CopyFromPcDocumentcpyfrmpcd.setFromFolder(String fromFolder){
    	this.fromFolder = fromFolder;
    }



    public String CopyFromPcDocumentcpyfrmpcd.getInsertDbcsSosi(){
    	return this.insertDbcsSosi;
    }
    
    public void CopyFromPcDocumentcpyfrmpcd.setInsertDbcsSosi(String insertDbcsSosi){
    	this.insertDbcsSosi = insertDbcsSosi;
    }



    public String CopyFromPcDocumentcpyfrmpcd.getLibrary(){
    	return this.library;
    }
    
    public void CopyFromPcDocumentcpyfrmpcd.setLibrary(String library){
    	this.library = library;
    }



    public String CopyFromPcDocumentcpyfrmpcd.getLibrary1(){
    	return this.library1;
    }
    
    public void CopyFromPcDocumentcpyfrmpcd.setLibrary1(String library1){
    	this.library1 = library1;
    }



    public String CopyFromPcDocumentcpyfrmpcd.getReplaceOrAddRecords(){
    	return this.replaceOrAddRecords;
    }
    
    public void CopyFromPcDocumentcpyfrmpcd.setReplaceOrAddRecords(String replaceOrAddRecords){
    	this.replaceOrAddRecords = replaceOrAddRecords;
    }



    public String CopyFromPcDocumentcpyfrmpcd.getToFile(){
    	return this.toFile;
    }
    
    public void CopyFromPcDocumentcpyfrmpcd.setToFile(String toFile){
    	this.toFile = toFile;
    }



    public String CopyFromPcDocumentcpyfrmpcd.getToMember(){
    	return this.toMember;
    }
    
    public void CopyFromPcDocumentcpyfrmpcd.setToMember(String toMember){
    	this.toMember = toMember;
    }



    public String CopyFromPcDocumentcpyfrmpcd.getTranslateTable(){
    	return this.translateTable;
    }
    
    public void CopyFromPcDocumentcpyfrmpcd.setTranslateTable(String translateTable){
    	this.translateTable = translateTable;
    }




    public String CopyFromPcDocumentcpyfrmpcd.getFocusField(){
    	return focusField;
    }
    public void CopyFromPcDocumentcpyfrmpcd.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
}