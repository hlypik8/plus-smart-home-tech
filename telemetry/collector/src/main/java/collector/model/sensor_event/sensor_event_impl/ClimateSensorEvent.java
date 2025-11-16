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
public class ClimateSensorEvent extends SensorEvent {

    @NotNull
    private Integer temperatureC;

    @NotNull
    private Integer humidity;

    @NotNull
    private Integer co2Level;

    @Override
    public SensorEventType getType(){
        return SensorEventType.CLIMATE_SENSOR_EVENT;
    }
}
