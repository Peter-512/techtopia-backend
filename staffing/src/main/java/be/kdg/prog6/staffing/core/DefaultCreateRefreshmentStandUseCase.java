package be.kdg.prog6.staffing.core;

import be.kdg.prog6.staffing.ports.in.CreateRefreshmentStandCommand;
import be.kdg.prog6.staffing.ports.in.CreateRefreshmentStandUseCase;
import be.kdg.prog6.staffing.ports.out.RefreshmentStandSavePort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultCreateRefreshmentStandUseCase implements CreateRefreshmentStandUseCase {
	private final RefreshmentStandSavePort refreshmentStandSavePort;

	@Override
	@Transactional
	public void createRefreshmentStand(CreateRefreshmentStandCommand createRefreshmentStandCommand) {
		refreshmentStandSavePort.saveRefreshmentStand(createRefreshmentStandCommand);
	}
}
