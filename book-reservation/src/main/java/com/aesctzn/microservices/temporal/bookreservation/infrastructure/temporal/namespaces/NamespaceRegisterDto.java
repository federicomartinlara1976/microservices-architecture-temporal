package com.aesctzn.microservices.temporal.bookreservation.infrastructure.temporal.namespaces;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NamespaceRegisterDto implements Serializable  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2328299775781549044L;
	
	private String name;
    
	private String description;
    
	private Integer retention;
    
	private String emailOwner;
    
	private String historyArchivalURI;
    
	private String visibilityArchivalURI;

}
