package be.kdg.prog6.staffing.adapters.in;

import be.kdg.prog6.common.events.VisitorGateInteraction;
import be.kdg.prog6.staffing.ports.in.VisitorGateInteractionPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VisitorGateInteractionAdapter implements VisitorGateInteractionPort {
	@Override
	@EventListener
	public void visitorWentThroughGate(VisitorGateInteraction event) {
		log.info("EVENT +++++++++ VISITOR WENT THROUGH GATE +++++++++ event: {}", event);
	}
}
