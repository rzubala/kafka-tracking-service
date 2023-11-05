package dev.lydtech.tracking.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrackingStatusUpdated {
    private UUID orderId;
    private TrackingStatus status;
}
