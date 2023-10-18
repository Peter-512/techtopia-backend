package be.kdg.prog6.staffing.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VisitorGateInteractionRepository extends JpaRepository<VisitorGateInteractionJpaEntity, UUID> {
}
