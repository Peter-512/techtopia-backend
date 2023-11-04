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

	RefreshmentStand map(RefreshmentStandJpaEntity entity);

	RefreshmentStandDTO map(RefreshmentStand refreshmentStand);
}
