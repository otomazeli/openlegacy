// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.someorg.examples.screens;

import org.springframework.stereotype.Component;

privileged aspect SignOn_Aspect {
    
    declare @type: SignOn : @Component;
    
    
    

    public String SignOn.getPassword(){
    	return this.password;
    }
    
    public void SignOn.setPassword(String password){
    	this.password = password;
    }

    public String SignOn.getUser(){
    	return this.user;
    }
    
    public void SignOn.setUser(String user){
    	this.user = user;
    }

    
}