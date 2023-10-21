package be.kdg.prog6.common.facades;

import be.kdg.prog6.common.events.Event;
import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventMessage;

import java.util.Optional;

public interface TechTopiaEventHandler<T extends Event> {

	boolean appliesTo(EventCatalog eventType);

	Optional<TechTopiaEventHandler<T>> receive(EventMessage eventMessage);

	Event map(String eventBody);

	void handle(Event eventBody, EventCatalog eventType);
}
