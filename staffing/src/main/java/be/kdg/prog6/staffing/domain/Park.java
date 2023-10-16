package be.kdg.prog6.staffing.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Park {

	private static final Park INSTANCE = new Park();
	@Getter
	@Setter
	private Weather weather;
	@Getter
	private int visitors;

	private Park() {
		this.visitors = 0;
	}

	public static Park instance() {
		return INSTANCE;
	}

	public void addVisitor() {
		visitors++;
	}

	public void removeVisitor() {
		visitors--;
	}
}
