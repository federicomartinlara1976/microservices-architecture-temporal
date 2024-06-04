package com.aesctzn.microservices.starter.temporal.services.namespaces;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NamespaceRegisterDto implements Serializable  {

    /**
	 * 
	 */
	private static final long serialVersionUID = -484806242347395080L;
	
	private String name;
    private String description;
    private int retention;
    private String emailOwner;
    private String historyArchivalURI;
    private String visibilityArchivalURI;

}
