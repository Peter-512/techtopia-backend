package be.kdg.prog6.infopoints.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Gate {
	UUID gateUUID;
	Coordinates coordinates;
}
