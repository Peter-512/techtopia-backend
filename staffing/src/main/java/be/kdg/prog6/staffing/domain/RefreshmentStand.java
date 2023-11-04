package be.kdg.prog6.staffing.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RefreshmentStand {
	private RefreshmentStandUUID uuid;
	private Coordinates coordinates;
	private String name;
	private Category category;


	public record RefreshmentStandUUID(UUID uuid) {
	}
}
