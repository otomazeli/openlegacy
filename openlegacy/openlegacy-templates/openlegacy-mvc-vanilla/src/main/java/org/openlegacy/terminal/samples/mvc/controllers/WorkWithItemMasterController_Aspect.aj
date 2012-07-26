// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).
package org.openlegacy.terminal.samples.mvc.controllers;

import java.util.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.openlegacy.modules.table.Table;
import org.openlegacy.modules.table.TableWriter;
import org.openlegacy.terminal.ScreenEntity;
import org.openlegacy.terminal.TerminalSession;
import org.openlegacy.terminal.actions.TerminalActions;
import org.openlegacy.terminal.services.ScreenEntitiesRegistry;
import org.openlegacy.terminal.web.JsonSerializationUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;

import org.openlegacy.terminal.samples.model.WorkWithItemMaster;

privileged @SuppressWarnings("unused") aspect WorkWithItemMasterController_Aspect {

	@Inject
	private TerminalSession WorkWithItemMasterController.terminalSession;

	@Inject
	private ScreenEntitiesRegistry WorkWithItemMasterController.screenEntitiesRegistry;

	@Inject
	private TableWriter WorkWithItemMasterController.tableWriter;
	
	// handle page initial display
    @RequestMapping(method = RequestMethod.GET)
    public String WorkWithItemMasterController.show(Model uiModel) {
    	WorkWithItemMaster workWithItemMaster = terminalSession.getEntity(WorkWithItemMaster.class);
	uiModel.addAttribute("workWithItemMaster", workWithItemMaster);
	// show the resulting page for WorkWithItemMaster
        return "WorkWithItemMaster";
    }

    @RequestMapping(value="/help", method = RequestMethod.GET)
    public @ResponseBody String WorkWithItemMasterController.systemHelp(HttpServletRequest request) throws IOException {
    	URL resource = request.getSession().getServletContext().getResource("/help/WorkWithItemMaster.html");
    	String result = "";
    	if (resource != null){
    		result = IOUtils.toString(resource.openStream());
    	}
    	return result;
    }

	// handle submit action
    @RequestMapping(method = RequestMethod.POST)
    public String WorkWithItemMasterController.submit(WorkWithItemMaster workWithItemMaster, Model uiModel, HttpServletRequest httpServletRequest) {
	ScreenEntity resultScreenEntity = terminalSession.doAction(TerminalActions.ENTER(), workWithItemMaster);
	// go to the controller for the resulting screen name
		if (resultScreenEntity != null){
			String screenEntityName = screenEntitiesRegistry.get(resultScreenEntity.getClass()).getEntityClassName();
			if (httpServletRequest.getParameter("partial") != null){
				workWithItemMaster = terminalSession.getEntity(WorkWithItemMaster.class);
		    	uiModel.addAttribute("workWithItemMaster", workWithItemMaster);
		        return "WorkWithItemMaster";
			}
        	return "redirect:" + screenEntityName;
		}
    	return "redirect:/";
		
    }
    
	// handle help action
    @RequestMapping(params="action=help", method = RequestMethod.POST)
    public String WorkWithItemMasterController.help(WorkWithItemMaster workWithItemMaster, Model uiModel, HttpServletRequest httpServletRequest) {
	ScreenEntity resultScreenEntity = terminalSession.doAction(TerminalActions.F1(), workWithItemMaster);
	// go to the controller for the resulting screen name
		if (resultScreenEntity != null){
			String screenEntityName = screenEntitiesRegistry.get(resultScreenEntity.getClass()).getEntityClassName();
			if (httpServletRequest.getParameter("partial") != null){
				workWithItemMaster = terminalSession.getEntity(WorkWithItemMaster.class);
		    	uiModel.addAttribute("workWithItemMaster", workWithItemMaster);
		        return "WorkWithItemMaster";
			}
        	return "redirect:" + screenEntityName;
		}
    	return "redirect:/";
		
    }
	// handle exit action
    @RequestMapping(params="action=exit", method = RequestMethod.POST)
    public String WorkWithItemMasterController.exit(WorkWithItemMaster workWithItemMaster, Model uiModel, HttpServletRequest httpServletRequest) {
	ScreenEntity resultScreenEntity = terminalSession.doAction(TerminalActions.F3(), workWithItemMaster);
	// go to the controller for the resulting screen name
		if (resultScreenEntity != null){
			String screenEntityName = screenEntitiesRegistry.get(resultScreenEntity.getClass()).getEntityClassName();
			if (httpServletRequest.getParameter("partial") != null){
				workWithItemMaster = terminalSession.getEntity(WorkWithItemMaster.class);
		    	uiModel.addAttribute("workWithItemMaster", workWithItemMaster);
		        return "WorkWithItemMaster";
			}
        	return "redirect:" + screenEntityName;
		}
    	return "redirect:/";
		
    }
	// handle create action
    @RequestMapping(params="action=create", method = RequestMethod.POST)
    public String WorkWithItemMasterController.create(WorkWithItemMaster workWithItemMaster, Model uiModel, HttpServletRequest httpServletRequest) {
	ScreenEntity resultScreenEntity = terminalSession.doAction(TerminalActions.F6(), workWithItemMaster);
	// go to the controller for the resulting screen name
		if (resultScreenEntity != null){
			String screenEntityName = screenEntitiesRegistry.get(resultScreenEntity.getClass()).getEntityClassName();
			if (httpServletRequest.getParameter("partial") != null){
				workWithItemMaster = terminalSession.getEntity(WorkWithItemMaster.class);
		    	uiModel.addAttribute("workWithItemMaster", workWithItemMaster);
		        return "WorkWithItemMaster";
			}
        	return "redirect:" + screenEntityName;
		}
    	return "redirect:/";
		
    }
	// handle cancel action
    @RequestMapping(params="action=cancel", method = RequestMethod.POST)
    public String WorkWithItemMasterController.cancel(WorkWithItemMaster workWithItemMaster, Model uiModel, HttpServletRequest httpServletRequest) {
	ScreenEntity resultScreenEntity = terminalSession.doAction(TerminalActions.F12(), workWithItemMaster);
	// go to the controller for the resulting screen name
		if (resultScreenEntity != null){
			String screenEntityName = screenEntitiesRegistry.get(resultScreenEntity.getClass()).getEntityClassName();
			if (httpServletRequest.getParameter("partial") != null){
				workWithItemMaster = terminalSession.getEntity(WorkWithItemMaster.class);
		    	uiModel.addAttribute("workWithItemMaster", workWithItemMaster);
		        return "WorkWithItemMaster";
			}
        	return "redirect:" + screenEntityName;
		}
    	return "redirect:/";
		
    }
	// handle numberSeq action
    @RequestMapping(params="action=numberSeq", method = RequestMethod.POST)
    public String WorkWithItemMasterController.numberSeq(WorkWithItemMaster workWithItemMaster, Model uiModel, HttpServletRequest httpServletRequest) {
	ScreenEntity resultScreenEntity = terminalSession.doAction(TerminalActions.F6(), workWithItemMaster);
	// go to the controller for the resulting screen name
		if (resultScreenEntity != null){
			String screenEntityName = screenEntitiesRegistry.get(resultScreenEntity.getClass()).getEntityClassName();
			if (httpServletRequest.getParameter("partial") != null){
				workWithItemMaster = terminalSession.getEntity(WorkWithItemMaster.class);
		    	uiModel.addAttribute("workWithItemMaster", workWithItemMaster);
		        return "WorkWithItemMaster";
			}
        	return "redirect:" + screenEntityName;
		}
    	return "redirect:/";
		
    }
	// handle previous action
    @RequestMapping(params="action=previous", method = RequestMethod.POST)
    public String WorkWithItemMasterController.previous(WorkWithItemMaster workWithItemMaster, Model uiModel, HttpServletRequest httpServletRequest) {
	ScreenEntity resultScreenEntity = terminalSession.doAction(TerminalActions.PAGEUP(), workWithItemMaster);
	// go to the controller for the resulting screen name
		if (resultScreenEntity != null){
			String screenEntityName = screenEntitiesRegistry.get(resultScreenEntity.getClass()).getEntityClassName();
			if (httpServletRequest.getParameter("partial") != null){
				workWithItemMaster = terminalSession.getEntity(WorkWithItemMaster.class);
		    	uiModel.addAttribute("workWithItemMaster", workWithItemMaster);
		        return "WorkWithItemMaster";
			}
        	return "redirect:" + screenEntityName;
		}
    	return "redirect:/";
		
    }
	// handle next action
    @RequestMapping(params="action=next", method = RequestMethod.POST)
    public String WorkWithItemMasterController.next(WorkWithItemMaster workWithItemMaster, Model uiModel, HttpServletRequest httpServletRequest) {
	ScreenEntity resultScreenEntity = terminalSession.doAction(TerminalActions.PAGEDOWN(), workWithItemMaster);
	// go to the controller for the resulting screen name
		if (resultScreenEntity != null){
			String screenEntityName = screenEntitiesRegistry.get(resultScreenEntity.getClass()).getEntityClassName();
			if (httpServletRequest.getParameter("partial") != null){
				workWithItemMaster = terminalSession.getEntity(WorkWithItemMaster.class);
		    	uiModel.addAttribute("workWithItemMaster", workWithItemMaster);
		        return "WorkWithItemMaster";
			}
        	return "redirect:" + screenEntityName;
		}
    	return "redirect:/";
		
    }
	
	// export to excel
    @RequestMapping(value="/excel", method = RequestMethod.GET)
    public void WorkWithItemMasterController.excel(HttpServletResponse response) throws IOException {
    	List<WorkWithItemMaster.WorkWithItemMasterRecord> records = terminalSession.getModule(Table.class).collectOne(WorkWithItemMaster.class,WorkWithItemMaster.WorkWithItemMasterRecord.class);
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition", "attachment; filename=\"WorkWithItemMasterRecord.xls\"");
    	tableWriter.writeTable(records, response.getOutputStream());
    }
    
	
	@InitBinder
	public void WorkWithItemMasterController.initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}
