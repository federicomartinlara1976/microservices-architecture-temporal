package com.aesctzn.microservices.temporal.bookreservation.infrastructure.temporal.activities;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface HelloActivity {
    @ActivityMethod
    ActivityResult sendHello(String saludo);
}
