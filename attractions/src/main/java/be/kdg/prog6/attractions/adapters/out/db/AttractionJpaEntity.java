package be.kdg.prog6.attractions.adapters.out.db;

import be.kdg.prog6.attractions.domain.Attraction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table (name = "attractions")
public class AttractionJpaEntity {

	@Id
	@JdbcTypeCode (Types.VARCHAR)
	private UUID uuid;

	private int highThreshold;

	private int currentVisitors;

	@Enumerated (EnumType.STRING)
	private Attraction.THROUGHPUT throughput;

	@ColumnDefault ("0")
	private int currentWaitingTime;
}
