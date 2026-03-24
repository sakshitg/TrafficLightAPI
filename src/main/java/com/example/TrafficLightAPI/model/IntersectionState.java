package com.example.TrafficLightAPI.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter
@Setter
@Component
public class IntersectionState {
    private Map<Direction, LightState> lights = new EnumMap<>(Direction.class);
    private boolean paused;

    public IntersectionState() {
        for (Direction d : Direction.values()) {
            lights.put(d, LightState.RED);
        }
    }
}
