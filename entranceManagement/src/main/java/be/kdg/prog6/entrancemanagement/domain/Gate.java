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

	private Gate(GateUUID gateUUID) {
		this.gateUUID = gateUUID;
	}

	public record GateUUID(UUID uuid) {
	}
}
