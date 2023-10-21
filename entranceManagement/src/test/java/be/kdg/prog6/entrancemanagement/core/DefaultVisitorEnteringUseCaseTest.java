package be.kdg.prog6.entrancemanagement.core;

import be.kdg.prog6.entrancemanagement.domain.Ticket;
import be.kdg.prog6.entrancemanagement.ports.in.TransitionVisitorCommand;
import be.kdg.prog6.entrancemanagement.ports.in.VisitorEnteringUseCase;
import be.kdg.prog6.entrancemanagement.ports.out.TicketPort;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorPort;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorUpdatePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith (MockitoExtension.class)
class DefaultVisitorEnteringUseCaseTest {
	@Spy
	TicketPort ticketPort;
	@Mock
	VisitorPort visitorPort;
	@Mock
	List<VisitorUpdatePort> visitorUpdatePorts;

	@Test
	void visitorEnteringWithInvalidTicketIdShouldReturnFalse() {
		var ticketUUID = new Ticket.TicketUUID(UUID.randomUUID());

		VisitorEnteringUseCase useCase = new DefaultVisitorEnteringUseCase(ticketPort, visitorPort, visitorUpdatePorts);
		final boolean ticketValid = useCase.visitorEntering(new TransitionVisitorCommand(ticketUUID.uuid(), UUID.randomUUID()));

		assertFalse(ticketValid);
		verify(ticketPort, times(1)).loadTicket(ticketUUID);
	}

	@Test
	void visitorEnteringWithTicketForIncorrectDateShouldReturnFalse() {
		var ticketUUID = new Ticket.TicketUUID(UUID.randomUUID());
		final Ticket ticket = new Ticket();
		ticket.setTicketUUID(ticketUUID);
		ticket.setValidOn(LocalDate.of(1992, 11, 19));
		ticketPort.saveTicket(ticket);

		VisitorEnteringUseCase useCase = new DefaultVisitorEnteringUseCase(ticketPort, visitorPort, visitorUpdatePorts);
		final boolean ticketValid = useCase.visitorEntering(new TransitionVisitorCommand(ticketUUID.uuid(), UUID.randomUUID()));

		assertFalse(ticketValid);
		verify(ticketPort, times(1)).loadTicket(ticketUUID);
	}

	@Test
	void visitorEnteringWithValidTicketShouldReturnTrue() {
		var ticketUUID = new Ticket.TicketUUID(UUID.randomUUID());
		final Ticket ticket = new Ticket();
		ticket.setTicketUUID(ticketUUID);
		ticket.setValidOn(LocalDate.now());
		ticketPort.saveTicket(ticket);

		VisitorEnteringUseCase useCase = new DefaultVisitorEnteringUseCase(ticketPort, visitorPort, visitorUpdatePorts);
		final boolean ticketValid = useCase.visitorEntering(new TransitionVisitorCommand(ticketUUID.uuid(), UUID.randomUUID()));

		assertFalse(ticketValid);
		verify(ticketPort, times(1)).loadTicket(ticketUUID);
	}
}
