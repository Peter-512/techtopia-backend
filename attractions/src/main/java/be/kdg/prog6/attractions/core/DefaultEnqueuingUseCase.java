package be.kdg.prog6.attractions.core;

import be.kdg.prog6.attractions.domain.Attraction;
import be.kdg.prog6.attractions.ports.in.EnqueueCommand;
import be.kdg.prog6.attractions.ports.in.EnqueuingUseCase;
import be.kdg.prog6.attractions.ports.out.AttractionLoadPort;
import be.kdg.prog6.attractions.ports.out.AttractionUpdatePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultEnqueuingUseCase implements EnqueuingUseCase {
	private final AttractionLoadPort attractionLoadPort;
	private final List<AttractionUpdatePort> attractionUpdatePort;

	@Override
	public void enqueue(EnqueueCommand command) {
		final Optional<Attraction> optionalAttraction = attractionLoadPort.loadAttraction(command.attractionUUID()
		                                                                                         .uuid());
		if (optionalAttraction.isEmpty()) {
			log.error("Attraction with uuid {} not found", command.attractionUUID());
			return;
		}
		var attraction = optionalAttraction.get();
		attraction.enqueue();
		attraction.adjustThroughput();
		attractionUpdatePort.forEach(port -> port.updateAttraction(attraction));
	}
}
