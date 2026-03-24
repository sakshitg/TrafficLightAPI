package com.example.TrafficLightAPI.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HistoryRecord {
    private Direction direction;
    private LightState state;
    private LocalDateTime timestamp;

    public HistoryRecord(Direction direction, LightState state) {
        this.direction = direction;
        this.state = state;
        this.timestamp = LocalDateTime.now();
    }
}
