// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package apps.inventory.screens;

import java.util.List;
import org.openlegacy.terminal.ScreenEntity;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

privileged @SuppressWarnings("unused") aspect ItemDetails1_Aspect {
    
    declare @type: ItemDetails1 : @Component;
	declare @type: ItemDetails1 : @Scope("prototype");
    

    declare parents: ItemDetails1 implements ScreenEntity;
    private String ItemDetails1.focusField;
    
	
	
	
    

    public String ItemDetails1.getItemDescription(){
    	return this.itemDescription;
    }
    
    public void ItemDetails1.setItemDescription(String itemDescription){
    	this.itemDescription = itemDescription;
    }

    public ItemDetails2 ItemDetails1.getItemDetails2(){
    	return this.itemDetails2;
    }
    

    public String ItemDetails1.getItemNumber(){
    	return this.itemNumber;
    }
    


    public String ItemDetails1.getFocusField(){
    	return focusField;
    }
    public void ItemDetails1.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
}