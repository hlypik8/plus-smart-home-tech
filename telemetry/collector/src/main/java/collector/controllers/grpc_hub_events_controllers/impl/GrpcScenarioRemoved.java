package collector.controllers.grpc_hub_events_controllers.impl;

import collector.controllers.grpc_hub_events_controllers.GrpcHubEvent;
import collector.mappers.ProtoMapper;
import collector.services.CollectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;

@Component
@RequiredArgsConstructor
public class GrpcScenarioRemoved implements GrpcHubEvent {
    private final ProtoMapper mapper;
    private final CollectorService service;

    @Override
    public HubEventProto.PayloadCase getMessageType() {
        return HubEventProto.PayloadCase.SCENARIO_REMOVED;
    }

    @Override
    public void handle(HubEventProto event) {
        HubEventAvro avro = mapper.toAvro(event);
        service.sendHubData(avro);
    }
}
