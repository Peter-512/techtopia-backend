package be.kdg.prog6.entrancemanagement.ports.out;

public interface VisitorUpdatePort {
	void visitorEntered(VisitorGateTransitionCommand command);

	void visitorLeft(VisitorGateTransitionCommand command);
}
