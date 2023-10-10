package be.kdg.prog6.entrancemanagement.core;

import be.kdg.prog6.entrancemanagement.ports.in.VisitorEnteredUseCase;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorUpdatePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DefaultVisitorEnteredUseCase implements VisitorEnteredUseCase {
	private final List<VisitorUpdatePort> visitorUpdatePorts;

	@Override
	public void visitorEntered(int amount) {
		visitorUpdatePorts.forEach(port -> port.visitorEntered(amount));
	}
}
