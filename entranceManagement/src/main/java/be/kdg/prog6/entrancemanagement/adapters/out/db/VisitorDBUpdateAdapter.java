package be.kdg.prog6.entrancemanagement.adapters.out.db;

import be.kdg.prog6.entrancemanagement.ports.out.VisitorUpdatePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class VisitorDBUpdateAdapter implements VisitorUpdatePort {
	@Override
	public void visitorEntered(int amount) {
		//		TODO: implement saving to database
		log.info("Visitor entered");
	}

	@Override
	public void visitorLeft(int amount) {
		//		TODO: implement saving to database
		log.info("Visitor left");
	}
}
