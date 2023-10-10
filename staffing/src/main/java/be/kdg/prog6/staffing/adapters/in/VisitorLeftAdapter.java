package be.kdg.prog6.staffing.adapters.in;

import be.kdg.prog6.common.events.VisitorLeftEvent;
import be.kdg.prog6.staffing.ports.in.VisitorLeftPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VisitorLeftAdapter implements VisitorLeftPort {
	@Override
	@EventListener
	public void visitorLeft(VisitorLeftEvent event) {
		log.info("EVENT ++++++++ VISITOR(S) LEFT +++++++++ {}", event.amount());
	}
}
