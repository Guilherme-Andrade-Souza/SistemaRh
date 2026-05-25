package dev.sistema.SistemaRh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * Configuração de CORS para permitir requisições do frontend Angular (localhost:4200).
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Origens permitidas (frontend Angular em dev e possível produção)
        config.setAllowedOrigins(List.of(
            "http://localhost:4200",
            "http://localhost:4201"
        ));

        // Métodos HTTP permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));

        // Headers permitidos nas requisições
        config.setAllowedHeaders(List.of("*"));

        // Permite envio de cookies e credenciais (útil para futura autenticação)
        config.setAllowCredentials(true);

        // Cache de pre-flight em segundos (1 hora)
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
    }
}
