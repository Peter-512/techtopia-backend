package be.kdg.prog6.attractions.ports.out;

import be.kdg.prog6.attractions.domain.Attraction;

public interface AttractionUpdatePort {
	void updateAttraction(Attraction attraction);
}
