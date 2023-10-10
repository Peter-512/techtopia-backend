package be.kdg.prog6.entrancemanagement.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VisitorRepository extends JpaRepository<VisitorJpaEntity, UUID> {
	Optional<VisitorJpaEntity> findByVisitor(UUID uuid);

	Optional<VisitorJpaEntity> findByTicket(UUID uuid);

}
