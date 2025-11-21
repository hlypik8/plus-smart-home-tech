package collector.controllers.grpc_hub_events_controllers;

import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;

public interface GrpcHubEvent {
    HubEventProto.PayloadCase getMessageType();

    void handle(HubEventProto event);
}
