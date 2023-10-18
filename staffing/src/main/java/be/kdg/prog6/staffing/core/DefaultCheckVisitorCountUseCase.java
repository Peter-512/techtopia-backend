package be.kdg.prog6.staffing.core;

import be.kdg.prog6.staffing.domain.Park;
import be.kdg.prog6.staffing.ports.in.CheckVisitorCountUseCase;
import org.springframework.stereotype.Service;

@Service
public class DefaultCheckVisitorCountUseCase implements CheckVisitorCountUseCase {

	@Override
	public void checkVisitorCount() {
		final int visitors = Park.instance().getVisitors();
	}
}
