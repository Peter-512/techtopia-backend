package be.kdg.prog6.config.amqp;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopology {

	public static final String TECHTOPIA_EVENTS_FAN_OUT = "techtopia-events";
	public static final String GATE_INTERACTION_EVENTS_QUEUE = "gate-interaction-queue";
	public static final String TICKET_BOUGHT_QUEUE = "ticket-bought-queue";
	public static final String SAVE_TICKET_COMMANDS = "save-ticket-commands";

	@Bean
	FanoutExchange techtopiaEventsExchange() {
		return new FanoutExchange(TECHTOPIA_EVENTS_FAN_OUT);
	}

	@Bean
	Queue gateInteractionEventsQueue() {
		return new Queue(GATE_INTERACTION_EVENTS_QUEUE);
	}

	@Bean
	Binding eventsBinding(FanoutExchange techtopiaEventsExchange, Queue gateInteractionEventsQueue) {
		return BindingBuilder.bind(gateInteractionEventsQueue).to(techtopiaEventsExchange);
	}


	@Bean
	TopicExchange saveTicketCommandExchange() {
		return new TopicExchange(SAVE_TICKET_COMMANDS);
	}

	@Bean
	Queue ticketBoughtEventsQueue() {
		return new Queue(TICKET_BOUGHT_QUEUE);
	}

	@Bean
	Binding saveTicketComandBinding(TopicExchange saveTicketCommandExchange, Queue ticketBoughtEventsQueue) {
		return BindingBuilder.bind(ticketBoughtEventsQueue)
		                     .to(saveTicketCommandExchange)
		                     .with("ticket.#");
	}
}
