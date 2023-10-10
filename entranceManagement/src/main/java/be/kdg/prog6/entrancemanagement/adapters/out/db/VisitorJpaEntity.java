package be.kdg.prog6.entrancemanagement.adapters.out.db;

import be.kdg.prog6.entrancemanagement.domain.Visitor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.NaturalId;

import java.sql.Types;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table (schema = "entrance", name = "entrance.visitor")
public class VisitorJpaEntity {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@NaturalId
	@JdbcTypeCode (Types.VARCHAR)
	@Setter
	private UUID visitor;
	@Setter
	@JdbcTypeCode (Types.VARCHAR)
	private UUID ticket;

	public VisitorJpaEntity(Visitor visitor) {
		this.visitor = visitor.getVisitorUUID().uuid();
		this.ticket = visitor.getTicket().getTicketUUID().uuid();
	}
}
