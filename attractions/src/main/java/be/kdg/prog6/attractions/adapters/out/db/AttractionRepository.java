package be.kdg.prog6.attractions.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttractionRepository extends JpaRepository<AttractionJpaEntity, UUID> {
}
