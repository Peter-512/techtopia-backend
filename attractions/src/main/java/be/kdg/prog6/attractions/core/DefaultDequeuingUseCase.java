package be.kdg.prog6.attractions.core;

import be.kdg.prog6.attractions.domain.Attraction;
import be.kdg.prog6.attractions.ports.in.DequeueCommand;
import be.kdg.prog6.attractions.ports.in.DequeuingUseCase;
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
public class DefaultDequeuingUseCase implements DequeuingUseCase {
	private final List<AttractionUpdatePort> attractionUpdatePort;
	private final AttractionLoadPort attractionLoadPort;

	@Override
	public void dequeue(DequeueCommand command) {
		final Optional<Attraction> optionalAttraction = attractionLoadPort.loadAttraction(command.attractionUUID()
		                                                                                         .uuid());
		if (optionalAttraction.isEmpty()) {
			log.error("Attraction with uuid {} not found", command.attractionUUID());
			return;
		}
		var attraction = optionalAttraction.get();
		attraction.dequeue();
		attraction.adjustThroughput();
		attractionUpdatePort.forEach(port -> port.updateAttraction(attraction));
	}
}