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
