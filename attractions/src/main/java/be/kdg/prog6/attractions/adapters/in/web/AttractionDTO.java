package be.kdg.prog6.attractions.adapters.in.web;

import be.kdg.prog6.attractions.domain.Attraction;
import lombok.Data;

@Data
public class AttractionDTO {
	Attraction.THROUGHPUT throughput;
	int threshold;
}
