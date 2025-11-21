package collector.controllers;

import collector.controllers.grpc_hub_events_controllers.GrpcHubEvent;
import collector.controllers.grpc_sensor_events_controllers.GrpcSensorEvent;
import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.yandex.practicum.grpc.telemetry.collector.CollectorControllerGrpc;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@GrpcService
public class EventsController extends CollectorControllerGrpc.CollectorControllerImplBase {

    private final Map<HubEventProto.PayloadCase, GrpcHubEvent> deviceHandlers;
    private final Map<SensorEventProto.PayloadCase, GrpcSensorEvent> sensorHandlers;

    public EventsController(Set<GrpcHubEvent> deviceHandlers, Set<GrpcSensorEvent> sensorHandlers) {
        this.deviceHandlers = deviceHandlers.stream()
                .collect(Collectors.toMap(GrpcHubEvent::getMessageType, Function.identity()));
        this.sensorHandlers = sensorHandlers.stream()
                .collect(Collectors.toMap(GrpcSensorEvent::getMessageType, Function.identity()));
    }

    public void collectSensorEvent(SensorEventProto request, StreamObserver<Empty> response) {
        try {
            GrpcSensorEvent event = sensorHandlers.get(request.getPayloadCase());
            event.handle(request);
            response.onNext(Empty.getDefaultInstance());
            response.onCompleted();
        } catch (Exception e) {
            response.onError(new StatusRuntimeException(
                    Status.INTERNAL
                            .withDescription(e.getLocalizedMessage())
                            .withCause(e)
            ));
        }
    }

    public void collectHubEvent(HubEventProto request, StreamObserver<Empty> response) {
        try {
            GrpcHubEvent event = deviceHandlers.get(request.getPayloadCase());
            event.handle(request);
            response.onNext(Empty.getDefaultInstance());
            response.onCompleted();
        } catch (Exception e) {
            response.onError(new StatusRuntimeException(
                    Status.INTERNAL
                            .withDescription(e.getLocalizedMessage())
                            .withCause(e)
            ));
        }
    }
}