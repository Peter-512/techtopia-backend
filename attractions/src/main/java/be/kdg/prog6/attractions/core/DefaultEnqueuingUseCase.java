package be.kdg.prog6.attractions.core;

import be.kdg.prog6.attractions.domain.Attraction;
import be.kdg.prog6.attractions.ports.in.EnqueueCommand;
import be.kdg.prog6.attractions.ports.in.EnqueuingUseCase;
import be.kdg.prog6.attractions.ports.out.AttractionPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultEnqueuingUseCase implements EnqueuingUseCase {
	private final AttractionPort attractionPort;

	@Override
	public void enqueue(EnqueueCommand command) {
		final Optional<Attraction> optionalAttraction = attractionPort.loadAttraction(command.attractionUUID().uuid());
		optionalAttraction.ifPresent(Attraction::enqueue);
		attractionPort.saveAttraction(optionalAttraction.orElseThrow());
	}
}
