package be.kdg.prog6.staffing.adapters.in.amqp;

import be.kdg.prog6.common.events.Event;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.facades.TechTopiaEventHandler;
import be.kdg.prog6.staffing.adapters.config.amqp.RabbitMQModuleTopology;
import be.kdg.prog6.staffing.ports.in.VisitorGateInteractionPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class RabbitMQVisitorGateInteractionEventHandler implements VisitorGateInteractionPort {
	private final List<TechTopiaEventHandler<? extends Event>> eventHandlers;
	private final Set<UUID> eventStore = new HashSet<>();

	@Override
	@RabbitListener (queues = RabbitMQModuleTopology.GATE_INTERACTION_EVENTS_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
	public void visitorWentThroughGate(EventMessage eventMessage) {
		log.info("EVENT +++++++++ VISITOR WENT THROUGH GATE +++++++++ event: {}", eventMessage);
		eventHandlers.stream()
		             .filter(eventHandler -> eventHandler.appliesTo(eventMessage.getEventHeader().getEventType()))
		             .forEach(eventHandler -> eventHandler.receive(eventMessage)
		                                                  .ifPresent(handler -> handler
				                                                  .handle(eventHandler.map(eventMessage.getEventBody()),
						                                                  eventMessage.getEventHeader()
						                                                              .getEventType())));
	}
}
