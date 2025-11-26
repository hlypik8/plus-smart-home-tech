package aggregator;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AggregatorStarterListener {

    private final AggregatorStarter aggregatorStarter;

    public AggregatorStarterListener(AggregatorStarter aggregatorStarter){
        this.aggregatorStarter = aggregatorStarter;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        aggregatorStarter.start();
    }
}
