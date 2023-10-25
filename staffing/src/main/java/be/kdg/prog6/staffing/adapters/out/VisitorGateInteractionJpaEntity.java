package be.kdg.prog6.staffing.adapters.out;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table (name = "visitor_gate_interactions")
public class VisitorGateInteractionJpaEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@JdbcTypeCode (Types.VARCHAR)
	private UUID uuid;

	@JdbcTypeCode (Types.VARCHAR)
	private UUID ticketUUID;

	@JdbcTypeCode (Types.VARCHAR)
	private UUID gateUUID;

	private String action;

	private LocalDateTime pit;
}
