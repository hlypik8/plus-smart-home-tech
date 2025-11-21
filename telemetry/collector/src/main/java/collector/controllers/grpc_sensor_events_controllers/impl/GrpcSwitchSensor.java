package collector.controllers.grpc_sensor_events_controllers.impl;

import collector.controllers.grpc_sensor_events_controllers.GrpcSensorEvent;
import collector.mappers.ProtoMapper;
import collector.services.CollectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;

@Component
@RequiredArgsConstructor
public class GrpcSwitchSensor implements GrpcSensorEvent {
    private final ProtoMapper mapper;
    private final CollectorService service;

    @Override
    public SensorEventProto.PayloadCase getMessageType() {
        return SensorEventProto.PayloadCase.SWITCH_SENSOR_EVENT;
    }

    @Override
    public void handle(SensorEventProto event) {
        SensorEventAvro avro = mapper.toAvro(event);
        service.sendSensorData(avro);
    }
}