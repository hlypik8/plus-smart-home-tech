package collector.controllers.grpc_sensor_events_controllers;

import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;

public interface GrpcSensorEvent {
    SensorEventProto.PayloadCase getMessageType();

    void handle(SensorEventProto event);
}
