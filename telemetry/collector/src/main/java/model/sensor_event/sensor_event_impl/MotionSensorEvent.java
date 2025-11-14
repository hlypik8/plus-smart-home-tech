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
public class MotionSensorEvent extends SensorEvent {

    @NotNull
    private Integer linkQuality;

    @NotNull
    private Boolean motion;

    @NotNull
    private Integer voltage;

    @Override
    public SensorEventType getType(){
        return SensorEventType.MOTION_SENSOR_EVENT;
    }
}
