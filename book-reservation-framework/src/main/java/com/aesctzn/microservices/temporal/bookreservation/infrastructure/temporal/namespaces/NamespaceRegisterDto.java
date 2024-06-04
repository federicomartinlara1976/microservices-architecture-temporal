package com.aesctzn.microservices.temporal.bookreservation.infrastructure.temporal.namespaces;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NamespaceRegisterDto implements Serializable  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2328299775781549044L;
	
	private String name;
    private String description;
    private int retention;
    private String emailOwner;
    private String historyArchivalURI;
    private String visibilityArchivalURI;

}
