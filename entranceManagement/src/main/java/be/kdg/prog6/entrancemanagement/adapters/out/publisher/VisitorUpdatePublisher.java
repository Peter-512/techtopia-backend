package be.kdg.prog6.entrancemanagement.adapters.out.publisher;

import be.kdg.prog6.common.events.VisitorEnteredEvent;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorUpdatePort;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class VisitorUpdatePublisher implements VisitorUpdatePort {
	private final ApplicationEventPublisher publisher;

	@Override
	public void visitorEntered(int amount) {
		publisher.publishEvent(new VisitorEnteredEvent(amount));
	}

	@Override
	public void visitorLeft(int amount) {
		publisher.publishEvent(new VisitorEnteredEvent(amount));
	}
}
