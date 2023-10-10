package be.kdg.prog6.staffing.adapters.in;

import be.kdg.prog6.common.events.VisitorEnteredEvent;
import be.kdg.prog6.staffing.ports.in.VisitorEnteredPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VisitorEnteredAdapter implements VisitorEnteredPort {
	@Override
	@EventListener
	public void visitorEntered(VisitorEnteredEvent event) {
		log.info("EVENT +++++++++ VISITOR(S) ENTERED +++++++++ {}", event.amount());
		log.info(event.toString());
	}
}
