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

    @Value("${collector.spring.kafka.topics.sensor-topic-name}")
    private String sensorTopic;
    @Value("${collector.spring.kafka.topics.hub-topic-name}")
    private String hubTopic;

    public void sendSensorData(SensorEventAvro event) {
        kafkaTemplate.send(sensorTopic, null, event.getTimestamp().toEpochMilli(), event.getHubId(), event)
                .whenComplete((result, exception) -> {
                    if (exception == null) {
                        log.info("Событие датчика {} успешно отправлено в kafka", event.getId());
                        log.debug(event.toString());
                    } else {
                        log.error("Не удалось отправить событие сенсора, {}", exception.toString());
                        log.debug(event.toString());
                    }
                });
    }

    public void sendHubData(HubEventAvro event) {
        kafkaTemplate.send(hubTopic, null, event.getTimestamp().toEpochMilli(), event.getHubId(), event)
                .whenComplete((result, exception) -> {
                    if (exception == null) {
                        log.info("Событие хаба {} успешно отправлено в kafka", event.getHubId());
                        log.debug(event.toString());
                    } else {
                        log.error("Не удалось отправить событие хаба, {}", exception.toString());
                        log.debug(event.toString());
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
