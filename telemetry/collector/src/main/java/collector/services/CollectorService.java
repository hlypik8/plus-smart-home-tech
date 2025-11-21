package collector.services;

import collector.kafka_producer.TelemetryProducerConfig;
import collector.mappers.AvroMapper;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final AvroMapper avroMapper;
    private final TelemetryProducerConfig config;

    @Value("${sensor.topic}")
    private String sensorTopic;
    @Value("${hub.topic}")
    private String hubTopic;

    public void sendSensorData(SensorEventAvro event) {
        log.debug(event.toString());
        kafkaTemplate.send(sensorTopic, null, event.getTimestamp().toEpochMilli(), event.getHubId(), event)
                .whenComplete((result, exception) -> {
                    if (exception == null) {
                        log.info("Событие сенсора успешно отправлено");
                    } else {
                        log.error("Не удалось отправить событие сенсора, {}", exception.toString());
                    }
                });
    }

    public void sendHubData(HubEventAvro event) {
        log.debug(event.toString());
        kafkaTemplate.send(hubTopic, null, event.getTimestamp().toEpochMilli(), event.getHubId(), event)
                .whenComplete((result, exception) -> {
                    if (exception == null) {
                        log.info("Событие хаба успешно отправлено");
                    } else {
                        log.error("Не удалось отправить событие хаба, {}", exception.toString());
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
