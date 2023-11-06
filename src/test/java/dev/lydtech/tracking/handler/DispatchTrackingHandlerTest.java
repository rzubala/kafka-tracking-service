package dev.lydtech.tracking.handler;

import dev.lydtech.dispatch.message.DispatchPreparing;
import dev.lydtech.tracking.service.TrackingService;
import dev.lydtech.tracking.utils.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DispatchTrackingHandlerTest {

    private TrackingService trackingServiceMock;

    private DispatchTrackingHandler dispatchTrackingHandler;

    @BeforeEach
    void setUp() {
        trackingServiceMock = mock(TrackingService.class);
        dispatchTrackingHandler = new DispatchTrackingHandler(trackingServiceMock);
    }

    @Test
    void listen_Success() throws Exception {
        DispatchPreparing dispatchPreparing = TestEventData.buildDispatchPreparingRandomData();
        dispatchTrackingHandler.listen(dispatchPreparing);

        verify(trackingServiceMock, times(1)).sendStatus(dispatchPreparing);
    }

    @Test
    void listen_ServiceThrowsException() throws Exception {
        DispatchPreparing dispatchPreparing = TestEventData.buildDispatchPreparingRandomData();
        doThrow(new RuntimeException("Service failure")).when(trackingServiceMock).sendStatus(dispatchPreparing);

        dispatchTrackingHandler.listen(dispatchPreparing);

        verify(trackingServiceMock, times(1)).sendStatus(dispatchPreparing);
    }
}