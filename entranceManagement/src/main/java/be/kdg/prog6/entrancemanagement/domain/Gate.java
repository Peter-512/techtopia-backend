package be.kdg.prog6.entrancemanagement.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Getter
@Slf4j
@ToString
public class Gate {
	private final GateUUID gateUUID;
	private final GateType gateType;

	private Gate(GateUUID gateUUID, GateType gateType) {
		this.gateUUID = gateUUID;
		this.gateType = gateType;
	}

	public static Gate openSingle() {
		return new Gate(new GateUUID(UUID.randomUUID()), GateType.SINGLE);
	}

	public static Gate openMultiple() {
		return new Gate(new GateUUID(UUID.randomUUID()), GateType.MULTIPLE);
	}

	public void close() {
		//		TODO: implement
		log.info("Gate {} closed", this);
	}

	enum GateType {
		SINGLE,
		MULTIPLE
	}

	public record GateUUID(UUID uuid) {
	}
}
