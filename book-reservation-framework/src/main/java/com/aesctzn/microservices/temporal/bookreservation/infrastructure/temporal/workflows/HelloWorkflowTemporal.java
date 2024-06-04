package com.aesctzn.microservices.temporal.bookreservation.infrastructure.temporal.workflows;

import java.time.Duration;

import com.aesctzn.microservices.temporal.bookreservation.infrastructure.temporal.activities.HelloActivity;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;

@WorkflowImpl(taskQueues = "helloQueue")
public class HelloWorkflowTemporal implements HelloWorkflow {

    private final HelloActivity helloActivity =
            Workflow.newActivityStub(
                    HelloActivity.class,
                    ActivityOptions.newBuilder()
                            .setStartToCloseTimeout(Duration.ofSeconds(60))
                            .setScheduleToCloseTimeout(Duration.ofSeconds(60))
                            .setScheduleToStartTimeout(Duration.ofSeconds(15))
                            .setRetryOptions(RetryOptions.newBuilder()
                                    .setInitialInterval(Duration.ofSeconds(5)) // Intervalo inicial entre reintentos
                                    .setMaximumAttempts(3) // Número máximo de reintentos
                                    .setDoNotRetry(String.valueOf(IllegalArgumentException.class)) // No volver a intentar para excepciones específicas
                                    .build())
                            .setHeartbeatTimeout(Duration.ofSeconds(60))
                            .build());




    @Override
    public WorkflowResult doHello(String saludo)  {

        helloActivity.sendHello(saludo);

        return new WorkflowResult();
    }

}
