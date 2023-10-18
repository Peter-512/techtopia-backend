package be.kdg.prog6.staffing.adapters.out;

import be.kdg.prog6.common.events.VisitorGateInteraction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VisitorGateInteractionMapper {
	VisitorGateInteractionMapper INSTANCE = Mappers.getMapper(VisitorGateInteractionMapper.class);

	VisitorGateInteraction map(VisitorGateInteractionJpaEntity entity);

	VisitorGateInteractionJpaEntity map(VisitorGateInteraction visitorGateInteraction);
}
