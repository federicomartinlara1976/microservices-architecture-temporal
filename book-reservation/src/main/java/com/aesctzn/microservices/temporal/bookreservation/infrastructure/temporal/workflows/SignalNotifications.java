package com.aesctzn.microservices.temporal.bookreservation.infrastructure.temporal.workflows;

import com.aesctzn.microservices.temporal.bookreservation.domain.Reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignalNotifications {

    private boolean sendNotification = false;

    private String destinatario = "";

    private String serviceName = "";

    private Reservation reservation;
}
