package analyzer.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import analyzer.model.Scenario;
import analyzer.model.Sensor;
import analyzer.repository.SensorRepository;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioAddedEventAvro;

@Service
@RequiredArgsConstructor
public class ScenarioMappingService {
    private final MapperClass mapper;
    private final SensorRepository sensorRepository;

    public Scenario completeScenarioMapping(ScenarioAddedEventAvro avro, String hubId) {
        Scenario scenario = mapper.mapFromAvro(avro, hubId);

        if (scenario.getConditions() != null) {
            scenario.getConditions().forEach(condition -> {
                String sensorId = condition.getSensor().getId();
                Sensor sensor = sensorRepository.findById(sensorId)
                        .orElseThrow(() -> new RuntimeException("Датчик не найден: " + sensorId));
                condition.setSensor(sensor);
            });
        }

        if (scenario.getActions() != null) {
            scenario.getActions().forEach(action -> {
                if (action.getSensor() != null && action.getSensor().getId() != null) {
                    sensorRepository.findById(action.getSensor().getId())
                            .ifPresent(action::setSensor);
                }
            });
        }

        return scenario;
    }
}