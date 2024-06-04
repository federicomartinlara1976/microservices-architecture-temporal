package com.aesctzn.microservices.temporal.bookreservation.infrastructure.temporal.workflows;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface SchedulerReservationsBillingWorkflow {

    @WorkflowMethod
    WorkflowResult doBilling(String initDate);

}
