package be.kdg.prog6.infopoints.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IPGateRepository extends JpaRepository<IPGateJpaEntity, UUID> {
}
