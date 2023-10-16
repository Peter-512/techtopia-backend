package be.kdg.prog6.common.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class EventHeader {

	private UUID eventID;
	private EventCatalog eventType;

	private EventHeader(Builder builder) {
		setEventID(builder.eventID);
		eventType = builder.eventCatalog;
	}

	@JsonIgnore
	public static Builder builder() {
		return new Builder();
	}

	@JsonIgnoreType
	public static final class Builder {
		private UUID eventID;

		private EventCatalog eventCatalog;

		private Builder() {
		}

		public Builder eventID(UUID val) {
			eventID = val;
			return this;
		}

		public Builder eventCatalog(EventCatalog val) {
			eventCatalog = val;
			return this;
		}

		public EventHeader build() {
			return new EventHeader(this);
		}
	}
}
