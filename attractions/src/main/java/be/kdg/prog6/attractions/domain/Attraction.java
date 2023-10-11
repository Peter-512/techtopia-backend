package be.kdg.prog6.attractions.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Attraction {
	private final AttractionUUID attractionUUID;
	private final int mediumThreshold;
	private final int highThreshold;
	private int currentVisitors;
	private THROUGHPUT throughput;

	public Attraction(AttractionUUID attractionUUID, int mediumThreshold, int highThreshold) {
		this.attractionUUID = attractionUUID;
		this.mediumThreshold = mediumThreshold;
		this.highThreshold = highThreshold;
		this.currentVisitors = 0;
		this.throughput = THROUGHPUT.LOW;
	}

	public void visitorEntersQueue() {
		currentVisitors++;
	}

	public void visitorLeavesQueue() {
		currentVisitors--;
	}
	
	public void setHighThroughput() {
		this.throughput = THROUGHPUT.HIGH;
	}

	public void setMediumThroughput() {
		this.throughput = THROUGHPUT.MEDIUM;
	}

	public void setLowThroughput() {
		this.throughput = THROUGHPUT.LOW;
	}

	public enum THROUGHPUT {
		LOW,
		MEDIUM,
		HIGH
	}

	public record AttractionUUID(UUID uuid) {
	}
}
