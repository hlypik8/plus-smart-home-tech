package collector.services;

import collector.kafka_producer.TelemetryProducerConfig;
import collector.model.sensor_event.sensor_event_impl.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import collector.mappers.EventsMapper;
import collector.model.hub_event.HubEvent;
import collector.model.hub_event.device_event_impl.DeviceAddedEvent;
import collector.model.hub_event.device_event_impl.DeviceRemovedEvent;
import collector.model.hub_event.scenario_event_impl.ScenarioAddedEvent;
import collector.model.hub_event.scenario_event_impl.ScenarioRemovedEvent;
import collector.model.sensor_event.SensorEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;

import static collector.kafka_producer.TelemetryProducerConfig.SENSORTOPIC;
import static collector.kafka_producer.TelemetryProducerConfig.HUBTOPIC;

@Slf4j
@Service
@RequiredArgsConstructor
public class CollectorService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final EventsMapper eventsMapper;
    private final TelemetryProducerConfig config;

    public ResponseEntity<Void> sendSensorData(SensorEvent sensorEvent) {
        try {
            SensorEventAvro sensorEventAvro = switch (sensorEvent) {
                case ClimateSensorEvent climateSensorEvent -> eventsMapper.toAvro(climateSensorEvent);
                case LightSensorEvent lightSensorEvent -> eventsMapper.toAvro(lightSensorEvent);
                case MotionSensorEvent motionSensorEvent -> eventsMapper.toAvro(motionSensorEvent);
                case SwitchSensorEvent switchSensorEvent -> eventsMapper.toAvro(switchSensorEvent);
                case TemperatureSensorEvent temperatureSensorEvent -> eventsMapper.toAvro(temperatureSensorEvent);
                default -> throw new IllegalArgumentException("Тип датчика с которым произошло событие неизвестен "
                        + sensorEvent.getType());
            };
            log.info("Отправка события датчика в kafka");

            long timestamp = sensorEventAvro.getTimestamp().toEpochMilli();

            kafkaTemplate.send(SENSORTOPIC, null, timestamp, sensorEventAvro.getHubId(), sensorEventAvro)
                    .whenComplete((result, exception) -> {
                        if (exception == null) {
                            log.info("Событие датчика успешно отправлено");
                        } else {
                            log.error("Не удалось отправить событие датчика");
                        }
                    });
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Void> sendHubData(HubEvent hubEvent) {
        try {
            HubEventAvro hubEventAvro = switch (hubEvent) {
                case DeviceAddedEvent deviceAddedEvent -> eventsMapper.toAvro(deviceAddedEvent);
                case DeviceRemovedEvent deviceRemovedEvent -> eventsMapper.toAvro(deviceRemovedEvent);
                case ScenarioAddedEvent scenarioAddedEvent -> eventsMapper.toAvro(scenarioAddedEvent);
                case ScenarioRemovedEvent scenarioRemovedEvent -> eventsMapper.toAvro(scenarioRemovedEvent);
                default ->
                        throw new IllegalArgumentException("Неподдерживаемый тип события хаба: " + hubEvent.getType());
            };

            log.info("Отправка события хаба в kafka");

            long timestamp = hubEventAvro.getTimestamp().toEpochMilli();

            kafkaTemplate.send(HUBTOPIC, null, timestamp, hubEventAvro.getHubId(), hubEventAvro)
                    .whenComplete((result, exception) -> {
                        if (exception == null) {
                            log.info("Событие хаба успешно отправлено в kafka");
                        } else {
                            log.error("Не удалось отправить событие хаба в kafka");
                        }
                    });
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Ошибка при обработке события для хаба");
            return ResponseEntity.badRequest().build();
        }
    }
}
