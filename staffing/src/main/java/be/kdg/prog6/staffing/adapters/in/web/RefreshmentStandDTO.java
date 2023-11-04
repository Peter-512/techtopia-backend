package be.kdg.prog6.staffing.adapters.in.web;

import lombok.Data;

import java.util.UUID;

@Data
public class RefreshmentStandDTO {
	private UUID uuid;
	private int x;
	private int y;
	private String name;
	private String category;
}
