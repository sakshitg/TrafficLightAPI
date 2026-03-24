package com.example.TrafficLightAPI.service;

import com.example.TrafficLightAPI.model.Direction;
import com.example.TrafficLightAPI.model.HistoryRecord;
import com.example.TrafficLightAPI.model.IntersectionState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrafficServiceTest {

    private TrafficService service;

    @Mock
    private IntersectionState state;

    @Mock
    private List<HistoryRecord> history;

    @BeforeEach
    void setup() {
        service = new TrafficService(state, history);
    }

    @Test
    void shouldChangeLightAndRecordHistory() {
        when(state.isPaused()).thenReturn(false);

        service.changeLight(Direction.NORTH);

        // Verify that isPaused was called
        verify(state).isPaused();

        // Verify that history was updated
        verify(history).add(any(HistoryRecord.class));
    }

    @Test
    void shouldThrowExceptionWhenPaused() {
        when(state.isPaused()).thenReturn(true);

        assertThrows(RuntimeException.class, () ->
                service.changeLight(Direction.NORTH)
        );

        // Ensure history is not updated when paused
        verifyNoInteractions(history);
    }

    @Test
    void shouldPauseAndResume() {
        service.pause();
        verify(state).setPaused(true);

        service.resume();
        verify(state).setPaused(false);
    }

    @Test
    void shouldHandleConcurrentRequestsSafely() throws InterruptedException {
        when(state.isPaused()).thenReturn(false);

        Thread t1 = new Thread(() -> service.changeLight(Direction.NORTH));
        Thread t2 = new Thread(() -> service.changeLight(Direction.SOUTH));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        verify(state, atLeast(2)).isPaused();

        // Verify that history.add was called at least twice
        verify(history, atLeast(2)).add(any(HistoryRecord.class));
    }
}