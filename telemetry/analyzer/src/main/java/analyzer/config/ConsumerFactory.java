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
    @Value("${bootstrap.server}")
    private String bootstrapServer;
    @Value("${key.deserializer}")
    private String keyDeserializer;
    @Value("${value.hub.deserializer}")
    private String valueHubDeserializer;
    @Value("${value.snapshot.deserializer}")
    private String valueSnapshotDeserializer;
    @Value("${client.hub.group}")
    private String clientHubGroup;
    @Value("${client.snapshot.group}")
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