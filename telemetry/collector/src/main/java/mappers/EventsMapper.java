package mappers;

import model.hub_event.device_event_impl.DeviceAction;
import model.hub_event.device_event_impl.DeviceAddedEvent;
import model.hub_event.device_event_impl.DeviceRemovedEvent;
import model.hub_event.scenario_event_impl.ScenarioAddedEvent;
import model.hub_event.scenario_event_impl.ScenarioCondition;
import model.hub_event.scenario_event_impl.ScenarioRemovedEvent;
import model.sensor_event.sensor_event_impl.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import ru.yandex.practicum.kafka.telemetry.event.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class EventsMapper {

    @Mapping(target = "payload", source = ".", qualifiedByName = "toClimatePayload")
    public abstract SensorEventAvro toAvro(ClimateSensorEvent event);

    @Mapping(target = "payload", source = ".", qualifiedByName = "toLightPayload")
    public abstract SensorEventAvro toAvro(LightSensorEvent event);

    @Mapping(target = "payload", source = ".", qualifiedByName = "toMotionPayload")
    public abstract SensorEventAvro toAvro(MotionSensorEvent event);

    @Mapping(target = "payload", source = ".", qualifiedByName = "toSwitchPayload")
    public abstract SensorEventAvro toAvro(SwitchSensorEvent event);

    @Mapping(target = "payload", source = ".", qualifiedByName = "toTemperaturePayload")
    public abstract SensorEventAvro toAvro(TemperatureSensorEvent event);

    @Mapping(target = "payload", source = ".", qualifiedByName = "toDeviceAddedPayload")
    public abstract HubEventAvro toAvro(DeviceAddedEvent event);

    @Mapping(target = "payload", source = ".", qualifiedByName = "toDeviceRemovedPayload")
    public abstract HubEventAvro toAvro(DeviceRemovedEvent event);

    @Mapping(target = "payload", source = ".", qualifiedByName = "toScenarioAddedPayload")
    public abstract HubEventAvro toAvro(ScenarioAddedEvent event);

    @Mapping(target = "payload", source = ".", qualifiedByName = "toScenarioRemovedPayload")
    public abstract HubEventAvro toAvro(ScenarioRemovedEvent event);

    @Named("toClimatePayload")
    protected ClimateSensorAvro toClimatePayload(ClimateSensorEvent event){
        ClimateSensorAvro payload = new ClimateSensorAvro();
        payload.setTemperatureC(event.getTemperatureC());
        payload.setHumidity(event.getHumidity());
        payload.setCo2Level(event.getCo2Level());
        return payload;
    }

    @Named("toLightPayload")
    protected LightSensorAvro toLightPayload(LightSensorEvent event) {
        LightSensorAvro payload = new LightSensorAvro();
        payload.setLinkQuality(event.getLinkQuality());
        payload.setLuminosity(event.getLuminosity());
        return payload;
    }

    @Named("toMotionPayload")
    protected MotionSensorAvro toMotionPayload(MotionSensorEvent event) {
        MotionSensorAvro payload = new MotionSensorAvro();
        payload.setLinkQuality(event.getLinkQuality());
        payload.setMotion(event.getMotion());
        payload.setVoltage(event.getVoltage());
        return payload;
    }

    @Named("toSwitchPayload")
    protected SwitchSensorAvro toSwitchPayload(SwitchSensorEvent event) {
        SwitchSensorAvro payload = new SwitchSensorAvro();
        payload.setState(event.getState());
        return payload;
    }

    @Named("toTemperaturePayload")
    protected TemperatureSensorAvro toTemperaturePayload(TemperatureSensorEvent event) {
        TemperatureSensorAvro payload = new TemperatureSensorAvro();
        payload.setTemperatureC(event.getTemperatureC());
        payload.setTemperatureF(event.getTemperatureF());
        return payload;
    }

    @Named("toDeviceAddedPayload")
    protected DeviceAddedEventAvro toDeviceAddedPayload(DeviceAddedEvent event) {
        DeviceAddedEventAvro payload = new DeviceAddedEventAvro();
        payload.setDeviceId(event.getId());
        payload.setType(DeviceTypeAvro.valueOf(event.getDeviceType().name()));
        return payload;
    }

    @Named("toDeviceRemovedPayload")
    protected DeiceRemovedEventAvro toDeviceRemovedPayload(DeviceRemovedEvent event) {
        DeiceRemovedEventAvro payload = new DeiceRemovedEventAvro();
        payload.setDeviceId(event.getId());
        return payload;
    }

    @Named("toScenarioAddedPayload")
    protected ScenarioAddedEventAvro toScenarioAddedPayload(ScenarioAddedEvent event) {
        ScenarioAddedEventAvro payload = new ScenarioAddedEventAvro();
        payload.setName(event.getName());
        payload.setConditions(ConditionsToMap(event.getConditions()));
        payload.setActions(ActionsToMap(event.getActions()));
        return payload;
    }

    @Named("toScenarioRemovedPayload")
    protected ScenarioRemovedEventAvro toScenarioRemovedPayload(ScenarioRemovedEvent event) {
        ScenarioRemovedEventAvro payload = new ScenarioRemovedEventAvro();
        payload.setName(event.getName());
        return payload;
    }

    protected List<ScenarioConditionAvro> ConditionsToMap(List<ScenarioCondition> conditions) {
        return conditions.stream()
                .map(this::ConditionToMap)
                .collect(Collectors.toList());
    }

    protected ScenarioConditionAvro ConditionToMap(ScenarioCondition condition) {
        ScenarioConditionAvro avro = new ScenarioConditionAvro();
        avro.setSensorId(condition.getSensorId());
        avro.setType(ConditionTypeAvro.valueOf(condition.getType().name()));
        avro.setOperation(ConditionOperationAvro.valueOf(condition.getOperation().name()));

        Object value = condition.getValue();
        if (value instanceof Boolean) {
            avro.setValue((Boolean) value);
        } else if (value instanceof Integer) {
            avro.setValue((Integer) value);
        } else {
            avro.setValue(null);
        }

        return avro;
    }

    protected List<DeviceActionAvro> ActionsToMap(List<DeviceAction> actions) {
        return actions.stream()
                .map(this::ActionToMap)
                .collect(Collectors.toList());
    }

    protected DeviceActionAvro ActionToMap(DeviceAction action) {
        DeviceActionAvro avro = new DeviceActionAvro();
        avro.setDeviceId(action.getSensorId());
        avro.setActionType(ActiontTypeAvro.valueOf(action.getType().name()));

        if (action.getValue() != null) {
            avro.setValue(action.getValue());
        } else {
            avro.setValue(null);
        }
        return avro;
    }
}
