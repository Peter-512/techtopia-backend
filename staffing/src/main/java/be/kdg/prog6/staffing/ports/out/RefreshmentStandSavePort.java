package be.kdg.prog6.staffing.ports.out;

import be.kdg.prog6.staffing.ports.in.CreateRefreshmentStandCommand;

public interface RefreshmentStandSavePort {
	void saveRefreshmentStand(CreateRefreshmentStandCommand command);
}
