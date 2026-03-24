package com.example.TrafficLightAPI.service;

import com.example.TrafficLightAPI.model.Direction;
import com.example.TrafficLightAPI.model.HistoryRecord;
import com.example.TrafficLightAPI.model.IntersectionState;
import com.example.TrafficLightAPI.model.LightState;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Getter
@Service
public class TrafficService {
    private final IntersectionState state;
    private final List<HistoryRecord> history;

    public TrafficService(IntersectionState state, List<HistoryRecord> history) {
        this.state = state;
        this.history = history;
    }

    public synchronized void changeLight(Direction direction) {
        if (state.isPaused()) {
            throw new RuntimeException("System is paused");
        }

        // Reset any other GREEN lights
        for (Map.Entry<Direction, LightState> entry : state.getLights().entrySet()) {
            if (entry.getValue() == LightState.GREEN && entry.getKey() != direction) {
                state.getLights().put(entry.getKey(), LightState.RED);
                history.add(new HistoryRecord(entry.getKey(), LightState.RED));
            }
        }

        state.getLights().put(direction, LightState.GREEN);
        history.add(new HistoryRecord(direction, LightState.GREEN));
    }

    public synchronized void pause() {
        state.setPaused(true);
    }

    public synchronized void resume() {
        state.setPaused(false);
    }
}
