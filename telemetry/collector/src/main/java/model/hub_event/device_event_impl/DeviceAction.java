package model.hub_event.device_event_impl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.hub_event.enums.ActionType;

@Getter
@Setter
@ToString
public class DeviceAction {
    private String sensorId;
    private ActionType type;
    private Integer value;
}
