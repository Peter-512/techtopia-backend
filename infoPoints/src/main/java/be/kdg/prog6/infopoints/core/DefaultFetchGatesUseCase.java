package be.kdg.prog6.infopoints.core;

import be.kdg.prog6.infopoints.domain.Gate;
import be.kdg.prog6.infopoints.ports.in.FetchGatesUseCase;
import be.kdg.prog6.infopoints.ports.out.LoadPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultFetchGatesUseCase implements FetchGatesUseCase {
	private final LoadPort<Gate> loadPort;

	@Override
	public List<Gate> fetchGates() {
		return loadPort.loadAll();
	}
}
