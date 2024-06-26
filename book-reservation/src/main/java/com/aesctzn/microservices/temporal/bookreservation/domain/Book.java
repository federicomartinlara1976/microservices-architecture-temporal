package com.aesctzn.microservices.temporal.bookreservation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    
	private Long id;
    
	private String title;
    
	private String author;
}