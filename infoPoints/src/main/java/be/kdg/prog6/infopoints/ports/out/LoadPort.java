package be.kdg.prog6.infopoints.ports.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadPort<T> {
	Optional<T> load(UUID attractionUUID);

	List<T> loadAll();
}
