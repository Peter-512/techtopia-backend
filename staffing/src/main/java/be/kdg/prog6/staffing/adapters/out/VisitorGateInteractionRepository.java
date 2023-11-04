package be.kdg.prog6.staffing.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface VisitorGateInteractionRepository extends JpaRepository<VisitorGateInteractionJpaEntity, UUID> {
	List<VisitorGateInteractionJpaEntity> findByGateUUID(UUID gateUUID);

	List<VisitorGateInteractionJpaEntity> findByGateUUIDAndPitBetween(UUID gateUUID, LocalDateTime pitStart, LocalDateTime pitEnd);

	List<VisitorGateInteractionJpaEntity> findByGateUUIDAndPitAfter(UUID gateUUID, LocalDateTime pit);

}
