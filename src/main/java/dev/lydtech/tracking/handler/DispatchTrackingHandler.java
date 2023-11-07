package dev.lydtech.tracking.handler;

import dev.lydtech.dispatch.message.DispatchPreparing;
import dev.lydtech.tracking.service.TrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DispatchTrackingHandler {
    private final TrackingService trackingService;

    @KafkaListener(
            id = "trackingConsumerClient",
            topics = "dispatch.tracking",
            groupId = "tracking.dispatch.tracking",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(DispatchPreparing dispatchPreparing) {
        log.info("Received dispatch preparing event " + dispatchPreparing.toString());
        try {
            trackingService.sendStatus(dispatchPreparing);
        } catch (Exception ex) {
            log.error("Tracking service failed", ex);
        }
    }
}
