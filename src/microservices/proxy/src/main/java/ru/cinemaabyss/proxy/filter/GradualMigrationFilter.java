package ru.cinemaabyss.proxy.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GradualMigrationFilter extends AbstractGatewayFilterFactory<GradualMigrationFilter.Config> {

    private final Random random = new Random();

    public GradualMigrationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String targetUrl = random.nextInt(100) < config.percent ? config.newServiceUrl : config.fallbackUrl;
            exchange.getAttributes().put("overrideUri", targetUrl);
            exchange.getRequest().mutate().header("X-Target-Service", targetUrl);

            var mutatedRequest = exchange.getRequest().mutate()
                    .uri(java.net.URI.create(targetUrl + exchange.getRequest().getURI().getPath()))
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        };
    }

    public static class Config {
        public int percent;
        public String newServiceUrl;
        public String fallbackUrl;

        public Config setPercent(int percent) {
            this.percent = percent;
            return this;
        }

        public Config setNewServiceUrl(String newServiceUrl) {
            this.newServiceUrl = newServiceUrl;
            return this;
        }

        public Config setFallbackUrl(String fallbackUrl) {
            this.fallbackUrl = fallbackUrl;
            return this;
        }
    }
}
