package be.kdg.prog6.attractions.ports.in;

import be.kdg.prog6.attractions.domain.Attraction;

import java.util.UUID;

public record UpdateAttractionCommand(UUID attractionUUID, Attraction.THROUGHPUT throughput, int threshold) {
}
