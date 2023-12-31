package dev.lydtech.tracking.service;

import dev.lydtech.dispatch.message.DispatchCompleted;
import dev.lydtech.dispatch.message.DispatchPreparing;
import dev.lydtech.dispatch.message.TrackingStatus;
import dev.lydtech.dispatch.message.TrackingStatusUpdated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrackingService {

    private final KafkaTemplate<String, Object> kafkaProducer;

    private final static String TRACKING_STATUS_TOPIC = "tracking.status";

    public void sendStatus(DispatchPreparing dispatchPreparing) throws Exception {
        TrackingStatusUpdated trackingStatusUpdated = TrackingStatusUpdated.builder()
                .orderId(dispatchPreparing.getOrderId())
                .status(TrackingStatus.PREPARING)
                .build();

        kafkaProducer.send(TRACKING_STATUS_TOPIC, trackingStatusUpdated).get();
    }

    public void sendStatus(DispatchCompleted dispatchCompleted) throws Exception {
        TrackingStatusUpdated trackingStatusUpdated = TrackingStatusUpdated.builder()
                .orderId(dispatchCompleted.getOrderId())
                .status(TrackingStatus.COMPLETED)
                .build();

        kafkaProducer.send(TRACKING_STATUS_TOPIC, trackingStatusUpdated).get();
    }
}
