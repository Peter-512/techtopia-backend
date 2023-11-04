package be.kdg.prog6.staffing.ports.in;

import be.kdg.prog6.staffing.domain.RefreshmentStand;

import java.util.List;

public interface FetchRefreshmentStandsUseCase {
	List<RefreshmentStand> fetchRefreshmentStands();
}
