package be.kdg.prog6.infopoints.adapters.out.db;

import be.kdg.prog6.infopoints.domain.Attraction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IPAttractionMapper {
	IPAttractionMapper INSTANCE = Mappers.getMapper(IPAttractionMapper.class);

	@Mapping (target = "coordinates.x", source = "x")
	@Mapping (target = "coordinates.y", source = "y")
	Attraction map(IPAttractionJpaEntity attractionJpaEntity);

	@Mapping (target = "x", source = "coordinates.x")
	@Mapping (target = "y", source = "coordinates.y")
	IPAttractionJpaEntity map(Attraction attraction);
}
