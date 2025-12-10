package analyzer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class ConsumerFactory {
    @Value("${spring.kafka.bootstrap-server}")
    private String bootstrapServer;
    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keyDeserializer;
    @Value("${spring.kafka.consumer.hub-event-value-deserializer}")
    private String valueHubDeserializer;
    @Value("${spring.kafka.consumer.snapshot-value-deserializer}")
    private String valueSnapshotDeserializer;
    @Value("${spring.kafka.consumer.hub-event-group-id}")
    private String clientHubGroup;
    @Value("${spring.kafka.consumer.snapshot-group-id}")
    private String clientSnapshotGroup;


    @Bean
    public KafkaConsumer<String, SpecificRecordBase> createSnapshotConsumer() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueSnapshotDeserializer);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, clientSnapshotGroup);

        log.info("Создание консьюмера снепшотов для анализатора");
        return new KafkaConsumer<>(configProps);
    }

    @Bean
    public KafkaConsumer<String, SpecificRecordBase> createHubConsumer() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueHubDeserializer);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, clientHubGroup);

        log.info("Создание консьюмера хаба для анализатора");
        return new KafkaConsumer<>(configProps);
    }
}