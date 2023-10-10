package be.kdg.prog6.entrancemanagement.ports.out;

public interface VisitorUpdatePort {
	void visitorEntered(int amount);

	void visitorLeft(int amount);
}
