package dev.lydtech.tracking.integration;

import dev.lydtech.dispatch.message.TrackingStatusUpdated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class KafkaTestListener {
    private final static String TRACKING_STATUS_TOPIC = "tracking.status";  // output

    AtomicInteger trackingStatusCounter = new AtomicInteger(0);

    @KafkaListener(groupId = "KafkaIntegrationTest", topics = TRACKING_STATUS_TOPIC)
    void receiveTrackingStatus(@Payload TrackingStatusUpdated payload) {
        log.debug("Received TrackingStatusUpdated: " + payload);
        trackingStatusCounter.incrementAndGet();
    }
}
