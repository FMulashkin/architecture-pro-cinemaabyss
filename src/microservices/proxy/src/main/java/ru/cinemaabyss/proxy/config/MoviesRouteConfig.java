package ru.cinemaabyss.proxy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.cinemaabyss.proxy.filter.GradualMigrationFilter;

@Configuration
public class MoviesRouteConfig {

    @Value("${GRADUAL_MIGRATION:false}")
    private boolean gradualMigration;

    @Value("${MOVIES_MIGRATION_PERCENT:50}")
    private int migrationPercent;

    @Value("${MOVIES_SERVICE_URL:http://localhost:8081}")
    private String moviesServiceUrl;

    @Value("${MONOLITH_URL:http://localhost:8080}")
    private String monolithUrl;

    private final GradualMigrationFilter gradualMigrationFilter;

    public MoviesRouteConfig(GradualMigrationFilter gradualMigrationFilter) {
        this.gradualMigrationFilter = gradualMigrationFilter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("movies_migration_route", r -> {
                    if (gradualMigration) {
                        return r.path("/api/v1/movies/**")
                                .filters(f -> f.filter(
                                        gradualMigrationFilter.apply(
                                                new GradualMigrationFilter.Config()
                                                        .setPercent(migrationPercent)
                                                        .setNewServiceUrl(moviesServiceUrl)
                                                        .setFallbackUrl(monolithUrl)
                                        )
                                ))
                                .uri(monolithUrl); // заглушка — URI будет перезаписан фильтром
                    } else {
                        return r.path("/api/v1/movies/**")
                                .uri(moviesServiceUrl);
                    }
                })
                .build();
    }
}
