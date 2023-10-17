package be.kdg.prog6.staffing.adapters.in;

import be.kdg.prog6.common.events.Event;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.facades.TechTopiaEventHandler;
import be.kdg.prog6.staffing.adapters.config.amqp.RabbitMQModuleTopology;
import be.kdg.prog6.staffing.ports.in.VisitorGateInteractionPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class RabbitMQVisitorGateInteractionEventHandler implements VisitorGateInteractionPort {
	private final List<TechTopiaEventHandler<? extends Event>> eventHandlers;

	@Override
	@RabbitListener (queues = RabbitMQModuleTopology.GATE_INTERACTION_EVENTS_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
	public void visitorWentThroughGate(EventMessage eventMessage) {
		log.info("EVENT +++++++++ VISITOR WENT THROUGH GATE +++++++++ event: {}", eventMessage);
		eventHandlers.stream()
		             .filter(eventHandler -> eventHandler.appliesTo(eventMessage.getEventHeader().getEventType()))
		             .forEach(eventHandler -> eventHandler.receive(eventMessage)
		                                                  .handle(eventHandler.map(eventMessage.getEventBody()),
				                                                  eventMessage.getEventHeader().getEventType()));
	}
}
