package be.kdg.prog6.entrancemanagement.adapters.out.db;

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
@Setter
@NoArgsConstructor
@Table (name = "entrance_tickets")
public class TicketJpaEntity {

	@Id
	@JdbcTypeCode (Types.VARCHAR)
	private UUID ticket;

	private LocalDate validOn;
}
