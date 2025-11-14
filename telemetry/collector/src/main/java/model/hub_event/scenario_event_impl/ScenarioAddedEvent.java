package model.hub_event.scenario_event_impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import model.hub_event.HubEvent;
import model.hub_event.device_event_impl.DeviceAction;
import model.hub_event.enums.HubEventType;

import java.util.List;

@Getter
@Setter
public class ScenarioAddedEvent extends HubEvent {

    @NotBlank
    private String name;

    @NotNull
    private List<ScenarioCondition> conditions;

    @NotNull
    List<DeviceAction> actions;

    @Override
    public HubEventType getType() {
        return HubEventType.SCENARIO_ADDED;
    }
}
