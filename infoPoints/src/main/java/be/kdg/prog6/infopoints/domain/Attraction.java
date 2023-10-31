package be.kdg.prog6.infopoints.domain;

import lombok.Data;

import java.net.URL;
import java.util.List;
import java.util.UUID;

@Data
public class Attraction {
	private String name;
	private String description;
	private UUID attractionUUID;
	private URL iconUrl;
	private URL imageUrl;
	private int waitingTime;
	private Coordinates coordinates;
	private List<Tag> tags;
	private int minHeight;
	private int minAge;
	private boolean isOpen;
}
