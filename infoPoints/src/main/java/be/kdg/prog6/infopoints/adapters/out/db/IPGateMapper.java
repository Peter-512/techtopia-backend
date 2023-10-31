package be.kdg.prog6.infopoints.adapters.out.db;

import be.kdg.prog6.infopoints.domain.Gate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IPGateMapper {
	IPGateMapper INSTANCE = Mappers.getMapper(IPGateMapper.class);

	@Mapping (target = "coordinates.x", source = "x")
	@Mapping (target = "coordinates.y", source = "y")
	Gate map(IPGateJpaEntity gateJpaEntity);

	@Mapping (target = "x", source = "coordinates.x")
	@Mapping (target = "y", source = "coordinates.y")
	IPGateJpaEntity map(Gate gate);
}
