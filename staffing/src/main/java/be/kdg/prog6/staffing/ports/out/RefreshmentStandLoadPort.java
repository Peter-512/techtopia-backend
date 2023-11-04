package be.kdg.prog6.staffing.ports.out;

import be.kdg.prog6.staffing.domain.RefreshmentStand;

import java.util.List;

public interface RefreshmentStandLoadPort {
	List<RefreshmentStand> loadRefreshmentStands();
}
