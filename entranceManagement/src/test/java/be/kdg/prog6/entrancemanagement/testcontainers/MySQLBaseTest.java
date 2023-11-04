package be.kdg.prog6.entrancemanagement.testcontainers;


import be.kdg.prog6.entrancemanagement.adapters.out.db.TicketJpaEntity;
import be.kdg.prog6.entrancemanagement.adapters.out.db.TicketRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static be.kdg.prog6.entrancemanagement.testcontainers.VisitorControllerIntegrationTest.TICKET_UUID;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith (SpringExtension.class)
@Testcontainers
@ContextConfiguration (initializers = MySQLBaseTest.DataSourceInitializer.class)
public abstract class MySQLBaseTest {

	private static final MySQLContainer<?> DATABASE;

	static {
		DATABASE = new MySQLContainer<>("mysql:8.0.30");
		DATABASE.start();
	}

	private final LocalDate VALID_ON = LocalDate.now();
	@Autowired
	private TicketRepository repository;

	@BeforeEach
	void resetDatabase() {
		TicketJpaEntity ticket = new TicketJpaEntity();
		ticket.setTicket(TICKET_UUID);
		ticket.setValidOn(VALID_ON);
		repository.save(ticket);
	}

	@Test
	void containerIsRunning() {
		assertThat(DATABASE.isCreated()).isTrue();
		assertThat(DATABASE.isRunning()).isTrue();
	}

	public static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
		public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
			TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
					applicationContext,
					"spring.datasource.url=" + DATABASE.getJdbcUrl(),
					"spring.datasource.username=" + DATABASE.getUsername(),
					"spring.datasource.password=" + DATABASE.getPassword()
			);
		}
	}
}
