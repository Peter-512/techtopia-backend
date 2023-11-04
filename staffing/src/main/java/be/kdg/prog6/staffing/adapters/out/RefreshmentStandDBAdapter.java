package be.kdg.prog6.staffing.adapters.out;

import be.kdg.prog6.staffing.domain.RefreshmentStand;
import be.kdg.prog6.staffing.ports.in.CreateRefreshmentStandCommand;
import be.kdg.prog6.staffing.ports.out.RefreshmentStandLoadPort;
import be.kdg.prog6.staffing.ports.out.RefreshmentStandSavePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RefreshmentStandDBAdapter implements RefreshmentStandSavePort, RefreshmentStandLoadPort {
	private final RefreshmentStandRepository refreshmentStandRepository;
	private final RefreshmentStandMapper refreshmentStandMapper = RefreshmentStandMapper.INSTANCE;

	@Override
	public void saveRefreshmentStand(CreateRefreshmentStandCommand command) {
		refreshmentStandRepository.save(refreshmentStandMapper.map(command));
	}

	@Override
	public List<RefreshmentStand> loadRefreshmentStands() {
		return refreshmentStandRepository.findAll()
		                                 .stream()
		                                 .map(refreshmentStandMapper::map)
		                                 .toList();
	}
}
