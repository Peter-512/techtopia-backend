package be.kdg.prog6.staffing.adapters.out;

import be.kdg.prog6.staffing.domain.GateInteractionActivity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GateInteractionActivityMapper {
	GateInteractionActivityMapper INSTANCE = Mappers.getMapper(GateInteractionActivityMapper.class);

	GateInteractionActivity map(VisitorGateInteractionJpaEntity entity);
}
