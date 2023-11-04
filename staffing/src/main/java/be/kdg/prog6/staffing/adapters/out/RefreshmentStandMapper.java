package be.kdg.prog6.staffing.adapters.out;

import be.kdg.prog6.staffing.adapters.in.web.RefreshmentStandDTO;
import be.kdg.prog6.staffing.domain.RefreshmentStand;
import be.kdg.prog6.staffing.ports.in.CreateRefreshmentStandCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RefreshmentStandMapper {
	RefreshmentStandMapper INSTANCE = Mappers.getMapper(RefreshmentStandMapper.class);

	@Mapping (target = "uuid", ignore = true)
	RefreshmentStandJpaEntity map(CreateRefreshmentStandCommand command);

	@Mapping (target = "coordinates.y", source = "y")
	@Mapping (target = "coordinates.x", source = "x")
	@Mapping (target = "uuid.uuid", source = "uuid")
	RefreshmentStand map(RefreshmentStandJpaEntity entity);

	@Mapping (target = "y", source = "coordinates.y")
	@Mapping (target = "x", source = "coordinates.x")
	@Mapping (target = "uuid", source = "uuid.uuid")
	RefreshmentStandDTO map(RefreshmentStand refreshmentStand);
}
