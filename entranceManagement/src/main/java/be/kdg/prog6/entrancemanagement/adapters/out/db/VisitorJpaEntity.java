package be.kdg.prog6.entrancemanagement.adapters.out.db;

import be.kdg.prog6.entrancemanagement.domain.Visitor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table (name = "entrance_visitor")
public class VisitorJpaEntity {

	@Id
	@JdbcTypeCode (Types.VARCHAR)
	@Setter
	private UUID visitor;
	@Setter
	@JdbcTypeCode (Types.VARCHAR)
	private UUID ticket;
	@Enumerated (EnumType.STRING)
	private Visitor.State state = Visitor.State.ENTERED;

	public VisitorJpaEntity(UUID visitor, UUID ticket, Visitor.State state) {
		this.visitor = visitor;
		this.ticket = ticket;
		this.state = state;
	}

	public void leave() {
		this.state = Visitor.State.LEFT;
	}

	public void enter() {
		this.state = Visitor.State.ENTERED;
	}
}
