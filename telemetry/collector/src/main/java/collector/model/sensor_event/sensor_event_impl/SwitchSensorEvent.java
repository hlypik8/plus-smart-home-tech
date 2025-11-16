package collector.model.sensor_event.sensor_event_impl;

import collector.model.sensor_event.SensorEvent;
import collector.model.sensor_event.SensorEventType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class SwitchSensorEvent extends SensorEvent {

    @NotNull
    private Boolean state;

    @Override
    public SensorEventType getType(){
        return SensorEventType.SWITCH_SENSOR_EVENT;
    }
}
