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
public class TemperatureSensorEvent extends SensorEvent {

    @NotNull
    private Integer temperatureC;

    @NotNull
    private Integer temperatureF;

    @Override
    public SensorEventType getType(){
        return SensorEventType.TEMPERATURE_SENSOR_EVENT;
    }
}
