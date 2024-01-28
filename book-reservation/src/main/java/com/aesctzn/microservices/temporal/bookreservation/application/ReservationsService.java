package com.aesctzn.microservices.temporal.bookreservation.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReservationsService implements Reservations {

    private ManagementBookPay managementBookPayService;
    private ManagementBookStock managementBookStock;


}
