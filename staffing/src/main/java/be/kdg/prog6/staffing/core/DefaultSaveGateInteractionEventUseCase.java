package be.kdg.prog6.staffing.core;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.VisitorGateInteraction;
import be.kdg.prog6.staffing.domain.Park;
import be.kdg.prog6.staffing.ports.in.SaveVisitorGateInteractionEventUseCase;
import be.kdg.prog6.staffing.ports.out.VisitorGateInteractionPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultSaveGateInteractionEventUseCase implements SaveVisitorGateInteractionEventUseCase {
	private final VisitorGateInteractionPort visitorGateInteractionPort;

	@Override
	@Transactional
	public void registerGateInteraction(VisitorGateInteraction event, EventCatalog eventType) {
		switch (eventType) {
			case VISITOR_ENTERED -> Park.instance().addVisitor();
			case VISITOR_LEFT -> Park.instance().removeVisitor();
		}
		visitorGateInteractionPort.saveVisitorGateInteraction(event);
	}
}
