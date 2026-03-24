package com.example.TrafficLightAPI.controller;

import com.example.TrafficLightAPI.model.Direction;
import com.example.TrafficLightAPI.service.TrafficService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/traffic")
public class TrafficController {

    private final TrafficService service;

    public TrafficController(TrafficService service) {
        this.service = service;
    }

    @GetMapping("/state")
    public Object getState() {
        return service.getState();
    }

    @GetMapping("/history")
    public Object getHistory() {
        return service.getHistory();
    }

    @PostMapping("/change/{direction}")
    public String change(@PathVariable Direction direction) {
        service.changeLight(direction);
        return "Changed to " + direction;
    }

    @PostMapping("/pause")
    public String pause() {
        service.pause();
        return "Paused";
    }

    @PostMapping("/resume")
    public String resume() {
        service.resume();
        return "Resumed";
    }
}