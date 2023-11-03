package be.kdg.prog6.staffing.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@ToString
public class Park {

	private static final Park INSTANCE = new Park();
	@Setter
	private WeatherType weatherType;
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
