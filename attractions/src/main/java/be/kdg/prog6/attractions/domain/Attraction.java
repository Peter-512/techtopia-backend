package be.kdg.prog6.attractions.domain;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Attraction {
	private AttractionUUID attractionUUID;
	private int highThreshold;
	private int currentVisitors;
	private THROUGHPUT throughput;
	private int currentWaitingTime;

	public Attraction(AttractionUUID attractionUUID, int highThreshold) {
		this(attractionUUID, highThreshold, 0, THROUGHPUT.LOW, 0);
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

	public int calculateWaitingTime() {
		int accelerator = isHighThroughput() ? 2 : 1;
		return (currentVisitors * 20) / (highThreshold * accelerator);
	}

	public boolean hasWaitingTimeChanged() {
		var waitingTime = calculateWaitingTime();
		if (waitingTime != currentWaitingTime) {
			currentWaitingTime = waitingTime;
			return true;
		}
		return false;
	}

	public enum THROUGHPUT {
		LOW,
		HIGH
	}

	public record AttractionUUID(UUID uuid) {
	}
}
