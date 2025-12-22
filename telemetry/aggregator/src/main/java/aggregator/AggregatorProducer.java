package aggregator;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AggregatorProducer {

    @Value("${aggregator.spring.kafka.bootstrap-server}")
    private String bootstrapServer;
    @Value("${aggregator.spring.kafka.producer.key-serializer}")
    private String keySerializer;
    @Value("${aggregator.spring.kafka.producer.value-serializer}")
    private String valueSerializer;


    @Bean
    public KafkaProducer<String, SpecificRecordBase> createProducer() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);

        return new KafkaProducer<>(configProps);
    }
}
