package com.aesctzn.microservices.temporal.bookreservation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    
	private Book book;
    
	private LocalDate reservationDate;
    
	private String userName;
    
	private String status;
}
