package be.kdg.prog6.attractions.core;

import be.kdg.prog6.attractions.domain.Attraction;
import be.kdg.prog6.attractions.ports.in.UpdateAttractionCommand;
import be.kdg.prog6.attractions.ports.in.UpdateAttractionUseCase;
import be.kdg.prog6.attractions.ports.out.AttractionLoadPort;
import be.kdg.prog6.attractions.ports.out.AttractionUpdatePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultUpdateAttractionUseCase implements UpdateAttractionUseCase {
	private final List<AttractionUpdatePort> attractionUpdatePorts;
	private final AttractionLoadPort loadPort;

	@Override
	public void updateAttraction(UpdateAttractionCommand command) {
		final Optional<Attraction> optionalAttraction = loadPort.loadAttraction(command.attractionUUID());
		if (optionalAttraction.isEmpty()) {
			return;
		}

		var attraction = optionalAttraction.get();
		attraction.setThreshold(command.threshold());
		attraction.setThroughput(command.throughput());
		attractionUpdatePorts.forEach(port -> port.updateAttraction(attraction));
	}
}
