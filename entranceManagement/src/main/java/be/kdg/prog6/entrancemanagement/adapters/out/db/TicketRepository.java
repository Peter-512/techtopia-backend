package be.kdg.prog6.entrancemanagement.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<TicketJpaEntity, UUID> {
	Optional<TicketJpaEntity> findByTicket(UUID ticket);
}
