package collector.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import collector.model.hub_event.HubEvent;
import collector.model.sensor_event.SensorEvent;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> sendSensorData(@Valid @RequestBody SensorEvent sensorEvent){
        return collectorService.sendSensorData(sensorEvent);
    }

    @PostMapping("/hubs")
    public ResponseEntity<Void> sendHubData(@Valid @RequestBody HubEvent hubEvent){
        return collectorService.sendHubData(hubEvent);
    }
}
