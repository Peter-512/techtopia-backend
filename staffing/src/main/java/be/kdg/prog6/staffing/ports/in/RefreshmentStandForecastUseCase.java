package be.kdg.prog6.staffing.ports.in;

public interface RefreshmentStandForecastUseCase {
	int getForecast(CalculateRefreshmentStandsCommand command);

	boolean canHandle(CalculateRefreshmentStandsCommand command);
}
