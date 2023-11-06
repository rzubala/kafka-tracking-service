package dev.lydtech.tracking.utils;

import dev.lydtech.tracking.message.DispatchPreparing;
import dev.lydtech.tracking.message.TrackingStatus;
import dev.lydtech.tracking.message.TrackingStatusUpdated;

import java.util.UUID;

public class TestEventData {
    public static DispatchPreparing buildDispatchPreparingRandomData() {
        return DispatchPreparing.builder().orderId(UUID.randomUUID()).build();
    }

    public static TrackingStatusUpdated buildTrackingStatusUpdatedRandom() {
        return TrackingStatusUpdated.builder()
                .orderId(UUID.randomUUID())
                .status(TrackingStatus.IN_PROGRESS)
                .build();
    }

    public static TrackingStatusUpdated buildTrackingStatusUpdated(DispatchPreparing dispatchPreparing) {
        return TrackingStatusUpdated.builder()
                .orderId(dispatchPreparing.getOrderId())
                .status(TrackingStatus.IN_PROGRESS)
                .build();
    }
}
