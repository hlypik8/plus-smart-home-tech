package collector.model.hub_event.scenario_event_impl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import collector.model.hub_event.enums.ConditionOperation;
import collector.model.hub_event.enums.ConditionType;

@Getter
@Setter
@ToString
public class ScenarioCondition {
    private String sensorId;
    private ConditionType type;
    private ConditionOperation operation;
    private Integer value;
}
