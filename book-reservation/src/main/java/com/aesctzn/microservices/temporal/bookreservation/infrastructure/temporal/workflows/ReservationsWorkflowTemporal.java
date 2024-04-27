package com.aesctzn.microservices.temporal.bookreservation.infrastructure.temporal.workflows;

import com.aesctzn.microservices.temporal.bookreservation.domain.Reservation;
import com.aesctzn.microservices.temporal.bookreservation.infrastructure.temporal.activities.*;
import io.temporal.activity.ActivityOptions;
import io.temporal.activity.LocalActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class ReservationsWorkflowTemporal implements ReservationsWorkflow {

    private final DeductStockActivity deductStockActivity =
            Workflow.newActivityStub(
                    DeductStockActivity.class,
                    ActivityOptions.newBuilder()
                            .setStartToCloseTimeout(Duration.ofSeconds(60))
                            .setScheduleToCloseTimeout(Duration.ofSeconds(50))
                            .setScheduleToStartTimeout(Duration.ofSeconds(15))
                            .setRetryOptions(RetryOptions.newBuilder()
                                    .setInitialInterval(Duration.ofSeconds(5)) // Intervalo inicial entre reintentos
                                    .setMaximumAttempts(3) // Número máximo de reintentos
                                    .setDoNotRetry(String.valueOf(IllegalArgumentException.class)) // No volver a intentar para excepciones específicas
                                    .build())
                            .setHeartbeatTimeout(Duration.ofSeconds(5))
                            .build());

    private final PayReservationActivity payReservationActivity=
            Workflow.newLocalActivityStub(
                    PayReservationActivity.class,
                    LocalActivityOptions.newBuilder() .setStartToCloseTimeout(Duration.ofSeconds(2))
                            .setStartToCloseTimeout(Duration.ofHours(5))
                            .setRetryOptions(RetryOptions.newBuilder()
                                    .setInitialInterval(Duration.ofSeconds(5)) // Intervalo inicial entre reintentos
                                    .setMaximumAttempts(3) // Número máximo de reintentos
                                    .setDoNotRetry(String.valueOf(IllegalArgumentException.class)) // No volver a intentar para excepciones específicas
                                    .build())
                            .build());


    //Guardará resultados parciales del Workflow que podremos consultar
    private WorkflowResult result = new WorkflowResult();

    // Se guardaŕa el parámetro de entrada de la ejecución del Workflow para que se pueda consultar si es necesario
    private Reservation reservationInfo;

    private SignalNotifications signalNotifications = new SignalNotifications();

    private String titulo;
    private ActivityResult resultDeductStock;


    @Override
    public WorkflowResult doReservation(Reservation reservation) {
    	log.info("doReservation");
    	
        this.reservationInfo = reservation;

        titulo = reservation.getBook().getTitle();

        log.info("Ejecutando WF Reserva de libro " + reservation.getBook().getTitle());

        ActivityResult resultDeductStock = deductStockActivity.deductStock(reservation.getBook());
        log.info("Result summary: {}", resultDeductStock.getSummary());
        
        result.setSummary(result.getSummary() + resultDeductStock.getSummary());

        result.setSummary(result.getSummary()+resultDeductStock.getSummary());

        ActivityResult payReservationResult = payReservationActivity.doPay(reservation);
        log.info("Result summary: {}", payReservationResult.getSummary());

        result.setSummary(result.getSummary() + " Reserva confirmada " + payReservationResult.getSummary());

        // Parcial Status para consulta
        reservation.setStatus("PAY Complete. Waiting for Notification");

        //Esperamos a señal de servicio externo envíe una notificación
        //Workflow.await(()->signalNotifications.isSendNotification());

        // En funcion de la notificación podemos
        if(signalNotifications.getServiceName().equals("EMAIL")) {
            log.info("Envío completado con notificación con email");
        }
        else {
            log.info("Envío completado con metodo alternativo");
        }

        //Actualizacion del estado de la reserva para posterior consulta
        reservation.setStatus("PAY Complete. Notification Complete");

        log.info("Flujo completado correctamente");
        return result;
    }

    @Override
    public void sendNotification(SignalNotifications signalNotifications) {
        log.info("Notificación Recibida: {}", signalNotifications.toString());
        this.signalNotifications = signalNotifications;
    }

    @Override
    public WorkflowResult getCurrentWorkflowResult() {
        return result;
    }

    @Override
    public Reservation getReservationInfo() {
        return reservationInfo;
    }
}
