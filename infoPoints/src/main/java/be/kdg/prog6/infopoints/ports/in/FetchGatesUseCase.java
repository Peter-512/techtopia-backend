package be.kdg.prog6.infopoints.ports.in;

import be.kdg.prog6.infopoints.domain.Gate;

import java.util.List;

public interface FetchGatesUseCase {
	List<Gate> fetchGates();
}
