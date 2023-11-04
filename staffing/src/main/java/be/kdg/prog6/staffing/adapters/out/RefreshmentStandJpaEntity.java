package be.kdg.prog6.staffing.adapters.out;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
@Table (name = "refreshment_stands")
@Getter
@Setter
public class RefreshmentStandJpaEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@JdbcTypeCode ((Types.VARCHAR))
	private UUID uuid;
	private int x;
	private int y;
	private String name;
	private String category;
}
