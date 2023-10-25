package be.kdg.prog6.infopoints.domain;

import lombok.Data;

import java.net.URL;
import java.util.List;
import java.util.UUID;

@Data
public class Attraction {
	private final String name;
	private final String description;
	private final UUID attractionUUID;
	private final URL iconUrl;
	private final URL imageUrl;
	private final int waitingTime;
	private final Coordinates coordinates;
	private final List<Tag> tags;
	private final int minHeight;
	private final int minAge;
	private final boolean isOpen;
}
