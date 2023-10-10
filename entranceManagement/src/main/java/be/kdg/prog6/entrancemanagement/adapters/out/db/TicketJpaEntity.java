package be.kdg.prog6.entrancemanagement.adapters.out.db;

import be.kdg.prog6.entrancemanagement.domain.Ticket;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.NaturalId;

import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table (schema = "entrance", name = "entrance.ticket")
public class TicketJpaEntity {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@NaturalId
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
