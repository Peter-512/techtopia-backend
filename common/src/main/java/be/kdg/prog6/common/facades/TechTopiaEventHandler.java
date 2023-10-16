package be.kdg.prog6.common.facades;

import be.kdg.prog6.common.events.Event;
import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventMessage;

public interface TechTopiaEventHandler<T extends Event> {

	boolean appliesTo(EventCatalog eventType);

	default TechTopiaEventHandler<T> receive(EventMessage eventMessage) {
		//TODO: check if this is a duplicate message in the eventstore
		//if not handle the event
		return this;
	}

	Event map(String eventBody);

	void handle(Event eventBody, EventCatalog eventType);
}
