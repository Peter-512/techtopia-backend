package be.kdg.prog6.attractions.adapters.out.db;

import be.kdg.prog6.attractions.domain.Attraction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface AttractionMapper {
	AttractionMapper INSTANCE = Mappers.getMapper(AttractionMapper.class);

	@Mapping (source = "attractionUUID", target = "uuid")
	AttractionJpaEntity map(Attraction attraction);

	@Mapping (source = "uuid", target = "attractionUUID")
	Attraction map(AttractionJpaEntity attractionJpaEntity);

	default UUID map(Attraction.AttractionUUID uuid) {
		return uuid.uuid();
	}

	default Attraction.AttractionUUID map(UUID uuid) {
		return new Attraction.AttractionUUID(uuid);
	}
}
