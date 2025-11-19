package collector.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import collector.model.hub_event.HubEvent;
import collector.model.sensor_event.SensorEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import collector.services.CollectorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventsController {

    private final CollectorService collectorService;

    @PostMapping("/sensors")
    public void sendSensorData(@Valid @RequestBody SensorEvent sensorEvent){
        collectorService.sendSensorData(sensorEvent);
    }

    @PostMapping("/hubs")
    public void sendHubData(@Valid @RequestBody HubEvent hubEvent){
        collectorService.sendHubData(hubEvent);
    }
}
