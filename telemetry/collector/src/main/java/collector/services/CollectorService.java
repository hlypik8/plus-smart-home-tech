package collector.services;

import collector.kafka_producer.TelemetryProducerConfig;
import collector.model.sensor_event.sensor_event_impl.*;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import collector.mappers.EventsMapper;
import collector.model.hub_event.HubEvent;
import collector.model.hub_event.device_event_impl.DeviceAddedEvent;
import collector.model.hub_event.device_event_impl.DeviceRemovedEvent;
import collector.model.hub_event.scenario_event_impl.ScenarioAddedEvent;
import collector.model.hub_event.scenario_event_impl.ScenarioRemovedEvent;
import collector.model.sensor_event.SensorEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;


@Slf4j
@Service
@RequiredArgsConstructor
public class CollectorService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final EventsMapper eventsMapper;
    private final TelemetryProducerConfig config;

    @Value("${sensor.topic}")
    private String sensorTopic;
    @Value("${hub.topic}")
    private String hubTopic;

    public void sendSensorData(SensorEvent sensorEvent) {
        SensorEventAvro sensorEventAvro = switch (sensorEvent) {
            case ClimateSensorEvent climateSensorEvent -> eventsMapper.toAvro(climateSensorEvent);
            case LightSensorEvent lightSensorEvent -> eventsMapper.toAvro(lightSensorEvent);
            case MotionSensorEvent motionSensorEvent -> eventsMapper.toAvro(motionSensorEvent);
            case SwitchSensorEvent switchSensorEvent -> eventsMapper.toAvro(switchSensorEvent);
            case TemperatureSensorEvent temperatureSensorEvent -> eventsMapper.toAvro(temperatureSensorEvent);
            default -> throw new IllegalArgumentException("Тип датчика с которым произошло событие неизвестен "
                    + sensorEvent.getType());
        };
        log.info("Отправка события {} в kafka", sensorEvent.getType());
        log.debug(sensorEvent.toString());

        long timestamp = sensorEventAvro.getTimestamp().toEpochMilli();

        kafkaTemplate.send(sensorTopic, null, timestamp, sensorEventAvro.getHubId(), sensorEventAvro)
                .whenComplete((result, exception) -> {
                    if (exception == null) {
                        log.info("Событие датчика успешно отправлено");
                    } else {
                        log.error("Не удалось отправить событие датчика");
                    }
                });
    }

    public void sendHubData(HubEvent hubEvent) {
        HubEventAvro hubEventAvro = switch (hubEvent) {
            case DeviceAddedEvent deviceAddedEvent -> eventsMapper.toAvro(deviceAddedEvent);
            case DeviceRemovedEvent deviceRemovedEvent -> eventsMapper.toAvro(deviceRemovedEvent);
            case ScenarioAddedEvent scenarioAddedEvent -> eventsMapper.toAvro(scenarioAddedEvent);
            case ScenarioRemovedEvent scenarioRemovedEvent -> eventsMapper.toAvro(scenarioRemovedEvent);
            default -> throw new IllegalArgumentException("Неподдерживаемый тип события хаба: " + hubEvent.getType());
        };

        log.info("Отправка события хаба {} в kafka", hubEvent.getType());
        log.debug(hubEvent.toString());

        long timestamp = hubEventAvro.getTimestamp().toEpochMilli();

        kafkaTemplate.send(hubTopic, null, timestamp, hubEventAvro.getHubId(), hubEventAvro)
                .whenComplete((result, exception) -> {
                    if (exception == null) {
                        log.info("Событие хаба успешно отправлено в kafka");
                    } else {
                        log.error("Не удалось отправить событие хаба в kafka");
                    }
                });
    }

    @PreDestroy
    public void cleanup() {
        log.info("Завершение работы - отправка оставшихся сообщений в kafka");
        kafkaTemplate.flush();
        log.info("Cleanup завершен");
    }
}
