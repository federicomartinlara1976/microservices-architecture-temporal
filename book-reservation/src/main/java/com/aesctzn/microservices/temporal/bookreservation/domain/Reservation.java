package com.aesctzn.microservices.temporal.bookreservation.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Reservation {
    private Book book;
    private LocalDate reservationDate;
}
