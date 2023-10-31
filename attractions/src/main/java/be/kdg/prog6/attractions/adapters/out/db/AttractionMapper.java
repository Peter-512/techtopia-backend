package be.kdg.prog6.attractions.adapters.out.db;

import be.kdg.prog6.attractions.domain.Attraction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AttractionMapper {
	AttractionMapper INSTANCE = Mappers.getMapper(AttractionMapper.class);

	@Mapping (source = "attractionUUID.uuid", target = "uuid")
	AttractionJpaEntity map(Attraction attraction);

	@Mapping (source = "uuid", target = "attractionUUID.uuid")
	Attraction map(AttractionJpaEntity attractionJpaEntity);

}
