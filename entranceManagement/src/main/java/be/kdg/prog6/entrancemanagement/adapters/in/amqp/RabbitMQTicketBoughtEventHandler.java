package be.kdg.prog6.entrancemanagement.adapters.in.amqp;

import be.kdg.prog6.common.events.Event;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.facades.TechTopiaEventHandler;
import be.kdg.prog6.entrancemanagement.adapters.config.amqp.RabbitMQModuleTopology;
import be.kdg.prog6.entrancemanagement.ports.in.TicketBoughtPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class RabbitMQTicketBoughtEventHandler implements TicketBoughtPort {
	private final List<TechTopiaEventHandler<? extends Event>> eventHandlers;

	@Override
	@RabbitListener (queues = RabbitMQModuleTopology.TICKET_BOUGHT_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
	public void buyTicket(EventMessage eventMessage) {
		log.info("Ticket bought event received: {}", eventMessage);
		eventHandlers.stream()
		             .filter(eventHandler -> eventHandler.appliesTo(eventMessage.getEventHeader().getEventType()))
		             .forEach(eventHandler -> eventHandler.receive(eventMessage)
		                                                  .ifPresent(handler -> handler
				                                                  .handle(eventHandler.map(eventMessage.getEventBody()),
						                                                  eventMessage.getEventHeader()
						                                                              .getEventType())));
	}
}
