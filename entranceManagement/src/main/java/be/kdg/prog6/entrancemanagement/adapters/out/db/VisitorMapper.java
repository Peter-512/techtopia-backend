package be.kdg.prog6.entrancemanagement.adapters.out.db;

import be.kdg.prog6.entrancemanagement.domain.Ticket;
import be.kdg.prog6.entrancemanagement.domain.Visitor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ObjectFactory;

import java.util.UUID;

@Mapper
public interface VisitorMapper {
	VisitorMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(VisitorMapper.class);

	@ObjectFactory
	default Visitor create(UUID ticketUUID) {
		Visitor visitor = new Visitor();
		visitor.setVisitorUUID(new Visitor.VisitorUUID(UUID.randomUUID()));
		visitor.setTicketUUID(new Ticket.TicketUUID(ticketUUID));
		visitor.setState(null);
		return visitor;
	}

	@Mapping (target = "visitor", source = "visitorUUID")
	@Mapping (target = "ticket", source = "ticketUUID")
	VisitorJpaEntity map(Visitor visitor);

	@Mapping (target = "visitorUUID", source = "visitor")
	@Mapping (target = "ticketUUID", source = "ticket", qualifiedByName = "mapToTicket")
	Visitor map(VisitorJpaEntity visitorJpaEntity);

	default Visitor.VisitorUUID map(UUID uuid) {
		return new Visitor.VisitorUUID(uuid);
	}

	default UUID map(Visitor.VisitorUUID uuid) {
		return uuid.uuid();
	}

	@Named (value = "mapToTicket")
	default Ticket.TicketUUID mapToTicket(UUID uuid) {
		return new Ticket.TicketUUID(uuid);
	}

	default UUID map(Ticket.TicketUUID uuid) {
		return uuid.uuid();
	}
}
