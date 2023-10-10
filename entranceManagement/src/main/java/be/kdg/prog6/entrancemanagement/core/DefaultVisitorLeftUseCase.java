package be.kdg.prog6.entrancemanagement.core;

import be.kdg.prog6.entrancemanagement.ports.in.VisitorLeftUseCase;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorUpdatePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DefaultVisitorLeftUseCase implements VisitorLeftUseCase {
	private final List<VisitorUpdatePort> visitorUpdatePorts;

	@Override
	public void visitorLeft(int amount) {
		visitorUpdatePorts.forEach(port -> port.visitorLeft(amount));
	}
}
