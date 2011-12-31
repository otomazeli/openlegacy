// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package apps.inventory.screens;

import java.util.List;
import org.openlegacy.terminal.ScreenEntity;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

privileged @SuppressWarnings("unused") aspect InventoryManagement_Aspect {
    
    declare @type: InventoryManagement : @Component;
	declare @type: InventoryManagement : @Scope("prototype");
    

    declare parents: InventoryManagement implements ScreenEntity;
    private String InventoryManagement.focusField;
    
	
    

    public String InventoryManagement.getSelection(){
    	return this.selection;
    }
    
    public void InventoryManagement.setSelection(String selection){
    	this.selection = selection;
    }


    public String InventoryManagement.getFocusField(){
    	return focusField;
    }
    public void InventoryManagement.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
}