package be.kdg.prog6.infopoints.adapters.out.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table (name = "infopoint_gates")
public class IPGateJpaEntity {
	@Id
	@JdbcTypeCode (Types.VARCHAR)
	UUID gateUUID;
	int x;
	int y;
}
