package be.kdg.prog6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
@EnableScheduling
@EnableRetry
@ComponentScan (excludeFilters = {
		@ComponentScan.Filter (type = FilterType.REGEX, pattern = "be.kdg.prog6.*.*Application"),
		@ComponentScan.Filter (type = FilterType.REGEX, pattern = "be.kdg.prog6.*.*.ModuleConfig"),
		@ComponentScan.Filter (type = FilterType.REGEX, pattern = "be.kdg.prog6.*.*.ModuleTopology"),
})
public class CompleteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompleteApplication.class, args);
	}
}
