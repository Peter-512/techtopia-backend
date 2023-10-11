package be.kdg.prog6.entrancemanagement.adapters.out.db;

import be.kdg.prog6.entrancemanagement.domain.Ticket;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table (name = "entrance_ticket")
public class TicketJpaEntity {

	@Id
	@JdbcTypeCode (Types.VARCHAR)
	@Setter
	private UUID ticket;

	@Setter
	private LocalDate validOn;

	public TicketJpaEntity(Ticket ticket) {
		this.ticket = ticket.getTicketUUID().uuid();
		this.validOn = ticket.getValidOn();
	}

	public Ticket toDomain() {
		return Ticket.create(new Ticket.TicketUUID(ticket), validOn);
	}
}
