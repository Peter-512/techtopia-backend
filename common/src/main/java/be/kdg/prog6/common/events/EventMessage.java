package be.kdg.prog6.common.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventMessage {
	private EventHeader eventHeader;

	private String eventBody;

	private EventMessage(Builder builder) {
		setEventHeader(builder.eventHeader);
		setEventBody(builder.eventBody);
	}

	@JsonIgnore
	public static Builder builder() {
		return new Builder();
	}

	@Override
	public String toString() {
		return "EventMessage{" +
				"eventHeader=" + eventHeader +
				", eventBody='" + eventBody + '\'' +
				'}';
	}

	@JsonIgnoreType
	public static final class Builder {
		private EventHeader eventHeader;
		private String eventBody;

		private Builder() {
		}

		public Builder eventHeader(EventHeader val) {
			eventHeader = val;
			return this;
		}

		public Builder eventBody(String val) {
			eventBody = val;
			return this;
		}

		public EventMessage build() {
			return new EventMessage(this);
		}

	}
}
