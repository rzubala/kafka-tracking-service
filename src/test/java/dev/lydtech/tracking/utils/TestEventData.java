package dev.lydtech.tracking.utils;

import dev.lydtech.dispatch.message.DispatchCompleted;
import dev.lydtech.dispatch.message.DispatchPreparing;
import dev.lydtech.dispatch.message.TrackingStatus;
import dev.lydtech.dispatch.message.TrackingStatusUpdated;

import java.util.UUID;

public class TestEventData {
    public static DispatchPreparing buildDispatchPreparingRandomData() {
        return DispatchPreparing.builder().orderId(UUID.randomUUID()).build();
    }

    public static TrackingStatusUpdated buildTrackingStatusUpdatedRandom() {
        return TrackingStatusUpdated.builder()
                .orderId(UUID.randomUUID())
                .status(TrackingStatus.PREPARING)
                .build();
    }

    public static TrackingStatusUpdated buildTrackingStatusUpdated(DispatchPreparing dispatchPreparing) {
        return TrackingStatusUpdated.builder()
                .orderId(dispatchPreparing.getOrderId())
                .status(TrackingStatus.PREPARING)
                .build();
    }

    public static DispatchCompleted buildDispatchCompletedRandomData() {
        return DispatchCompleted.builder().orderId(UUID.randomUUID()).build();
    }
}
