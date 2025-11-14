package model.sensor_event.sensor_event_impl;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.sensor_event.SensorEvent;
import model.sensor_event.SensorEventType;

@Getter
@Setter
@ToString(callSuper = true)
public class LightSensorEvent extends SensorEvent {

    @NotNull
    private Integer linkQuality;

    @NotNull
    private Integer luminosity;

    @Override
    public SensorEventType getType(){
        return SensorEventType.LIGHT_SENSOR_EVENT;
    }
}
