package be.kdg.prog6.entrancemanagement.testcontainers;

import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.entrancemanagement.domain.Ticket;
import be.kdg.prog6.entrancemanagement.domain.Visitor;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorPort;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static be.kdg.prog6.entrancemanagement.adapters.config.amqp.RabbitMQModuleTopology.TECHTOPIA_EVENTS_FAN_OUT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class VisitorControllerIntegrationTest extends MySQLBaseTest {
	static final UUID TICKET_UUID = UUID.fromString("ef01c728-ce36-46b5-a110-84f53fdd9668");
	private static final String BASE_URL = "/visitor";
	private static final UUID GATE_UUID = UUID.fromString("ef01c728-ce36-46b5-a110-84f53fdd9668");
	@MockBean
	private RabbitTemplate rabbitTemplate;
	@MockBean
	private SimpleMessageListenerContainer simpleMessageListenerContainer;
	@Autowired
	private VisitorPort visitorPort;
	@Autowired
	private MockMvc mvc;

	@Test
	void visitorEntered() throws Exception {
		mvc.perform(post(BASE_URL + "/enter/" + TICKET_UUID + "/gate/" + GATE_UUID)
				   .with(jwt()))
		   .andExpect(status().isOk())
		   .andReturn();

		var optionalVisitor = visitorPort.loadVisitor(new Ticket.TicketUUID(TICKET_UUID));
		assertTrue(optionalVisitor.isPresent());

		var visitor = optionalVisitor.get();
		assertEquals(TICKET_UUID, visitor.getTicketUUID().uuid());
		assertEquals(Visitor.State.ENTERED, visitor.getState());

		verify(rabbitTemplate, times(1)).convertAndSend(eq(TECHTOPIA_EVENTS_FAN_OUT), eq("entrance.visitor.entered"), any(EventMessage.class));
	}
}
