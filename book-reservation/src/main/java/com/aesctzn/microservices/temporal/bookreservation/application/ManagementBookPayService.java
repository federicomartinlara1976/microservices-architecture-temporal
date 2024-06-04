package com.aesctzn.microservices.temporal.bookreservation.application;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aesctzn.microservices.temporal.bookreservation.domain.Reservation;

@Service
public class ManagementBookPayService implements ManagementBookPay {
    @Override
    public ResponseEntity<String> doPay(Reservation reservation) {
        return null;
    }
}
