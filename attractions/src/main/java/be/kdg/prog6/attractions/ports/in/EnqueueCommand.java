package be.kdg.prog6.attractions.ports.in;

import be.kdg.prog6.attractions.domain.Attraction;

public record EnqueueCommand(Attraction.AttractionUUID attractionUUID) {
}
