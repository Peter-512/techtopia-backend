package be.kdg.prog6.staffing.core;

import be.kdg.prog6.staffing.domain.RefreshmentStand;
import be.kdg.prog6.staffing.ports.in.FetchRefreshmentStandsUseCase;
import be.kdg.prog6.staffing.ports.out.RefreshmentStandLoadPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultFetchRefreshmentStandsUseCase implements FetchRefreshmentStandsUseCase {
	private final RefreshmentStandLoadPort refreshmentStandLoadPort;

	@Override
	public List<RefreshmentStand> fetchRefreshmentStands() {
		return refreshmentStandLoadPort.loadRefreshmentStands();
	}
}
