package be.kdg.prog6.parkticketing.core;

import be.kdg.prog6.common.events.TicketBoughtEvent;
import be.kdg.prog6.parkticketing.ports.in.TicketSoldUseCase;
import be.kdg.prog6.parkticketing.ports.out.TicketSoldPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultTicketSoldUseCase implements TicketSoldUseCase {
	private final List<TicketSoldPort> ports;

	@Override
	public void saveTicketSale(TicketBoughtEvent event) {
		ports.forEach(port -> port.buyTicket(event));
	}
}
