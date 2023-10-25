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
@Setter
@NoArgsConstructor
@Table (name = "entrance_visitors")
public class VisitorJpaEntity {

	@Id
	@JdbcTypeCode (Types.VARCHAR)
	private UUID visitor;
	@JdbcTypeCode (Types.VARCHAR)
	private UUID ticket;
	@Enumerated (EnumType.STRING)
	private Visitor.State state = Visitor.State.ENTERED;
}
