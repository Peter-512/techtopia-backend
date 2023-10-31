package be.kdg.prog6.infopoints.adapters.out.db;

import be.kdg.prog6.infopoints.domain.Gate;
import be.kdg.prog6.infopoints.ports.out.LoadPort;
import be.kdg.prog6.infopoints.ports.out.UpdatePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class IPGatePortDBAdapter implements UpdatePort<Gate>, LoadPort<Gate> {
	private final IPGateRepository gateRepository;
	private final IPGateMapper mapper = IPGateMapper.INSTANCE;

	@Override
	public Optional<Gate> load(UUID attractionUUID) {
		return gateRepository.findById(attractionUUID).map(mapper::map);
	}

	@Override
	public List<Gate> loadAll() {
		return gateRepository.findAll().stream().map(mapper::map).toList();
	}

	@Override
	public void update(Gate attraction) {
		gateRepository.save(mapper.map(attraction));
	}
}
