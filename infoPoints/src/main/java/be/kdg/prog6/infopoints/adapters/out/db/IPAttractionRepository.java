package be.kdg.prog6.infopoints.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IPAttractionRepository extends JpaRepository<IPAttractionJpaEntity, UUID> {
	@Query ("SELECT a FROM IPAttractionJpaEntity a LEFT JOIN FETCH a.tags")
	List<IPAttractionJpaEntity> getAllAttractionsWithTags();

	@Query ("""
			SELECT ip FROM IPAttractionJpaEntity ip
			JOIN ip.tags t
			WHERE ip.attractionUUID <> :attractionUUID
			AND t IN (SELECT t2 FROM IPAttractionJpaEntity ip2
			JOIN ip2.tags t2
			WHERE ip2.attractionUUID = :attractionUUID)
			GROUP BY ip
			HAVING COUNT(t) >= 2""")
	List<IPAttractionJpaEntity> getAllAttractionsWithSimilarTags(UUID attractionUUID);
}
