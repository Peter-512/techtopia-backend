package be.kdg.prog6.attractions.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Attraction {
	private AttractionUUID attractionUUID;
	private int highThreshold;
	private int currentVisitors;
	private THROUGHPUT throughput;

	public Attraction(AttractionUUID attractionUUID, int highThreshold) {
		this(attractionUUID, highThreshold, 0, THROUGHPUT.LOW);
	}

	public Attraction(AttractionUUID attractionUUID, int highThreshold, int currentVisitors, THROUGHPUT throughput) {
		this.attractionUUID = attractionUUID;
		this.highThreshold = highThreshold;
		this.currentVisitors = currentVisitors;
		this.throughput = throughput;
	}

	public void enqueue() {
		currentVisitors++;
	}

	public void dequeue() {
		currentVisitors--;
	}

	private void setHighThroughput() {
		this.throughput = THROUGHPUT.HIGH;
	}

	private void setLowThroughput() {
		this.throughput = THROUGHPUT.LOW;
	}

	private boolean isHighThroughput() {
		return this.throughput == THROUGHPUT.HIGH;
	}

	public void adjustThroughput() {
		if (isHighThroughput() && currentVisitors < highThreshold * 0.8) {
			setLowThroughput();
		} else if (!isHighThroughput() && currentVisitors > highThreshold) {
			setHighThroughput();
		}
	}

	public enum THROUGHPUT {
		LOW,
		HIGH
	}

	public record AttractionUUID(UUID uuid) {
	}
}
