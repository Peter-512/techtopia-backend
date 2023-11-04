package be.kdg.prog6.staffing.domain;

import java.util.UUID;

public class RefreshmentStand {
	private RefreshmentStandUUID uuid;
	private Coordinates coordinates;
	private String name;
	private Category category;

	public enum Category {
		FOOD,
		DRINKS,
		SNACKS
	}

	public record RefreshmentStandUUID(UUID uuid) {
	}
}
