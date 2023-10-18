package be.kdg.prog6.entrancemanagement.adapters.out.db;

import be.kdg.prog6.entrancemanagement.domain.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.UUID;

@Mapper
public interface TicketMapper {
	TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

	@Mapping (target = "ticket", source = "ticketUUID")
	TicketJpaEntity map(Ticket ticket);

	@Mapping (target = "ticketUUID", source = "ticket")
	Ticket map(TicketJpaEntity ticketJpaEntity);

	@ObjectFactory
	default Ticket create(UUID uuid, LocalDate validOn) {
		Ticket ticket = new Ticket();
		ticket.setTicketUUID(new Ticket.TicketUUID(uuid));
		ticket.setValidOn(validOn);
		return ticket;
	}

	default Ticket.TicketUUID map(UUID uuid) {
		return new Ticket.TicketUUID(uuid);
	}

	default UUID map(Ticket.TicketUUID uuid) {
		return uuid.uuid();
	}
}
