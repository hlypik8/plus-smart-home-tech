package aggregator;

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
public class AggregatorConsumer {
    @Value("${bootstrap.server}")
    private String bootstrapServer;
    @Value("${key.deserializer}")
    private String keyDeserializer;
    @Value("${value.deserializer}")
    private String valueDeserializer;
    @Value("${client.group}")
    private String clientGroup;


    @Bean
    public KafkaConsumer<String, SpecificRecordBase> createConsumer() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, clientGroup);

        return new KafkaConsumer<>(configProps);
    }
}