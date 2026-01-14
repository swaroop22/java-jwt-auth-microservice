package com.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * Enterprise-grade CORS (Cross-Origin Resource Sharing) configuration.
 * Provides secure and flexible cross-origin access control for the JWT Authentication API.
 * 
 * This configuration follows security best practices:
 * - Environment-specific allowed origins
 * - Explicit method and header allowlists
 * - Credential support for authenticated requests
 * - Configurable max age for preflight caching
 * - Secure exposed headers configuration
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    
    @Value("${cors.allowed-origins:http://localhost:3000,http://localhost:4200}")
    private String[] allowedOrigins;
    
    @Value("${cors.allowed-methods:GET,POST,PUT,DELETE,OPTIONS,PATCH}")
    private String[] allowedMethods;
    
    @Value("${cors.allowed-headers:*}")
    private String allowedHeaders;
    
    @Value("${cors.exposed-headers:Authorization,Content-Type,X-Total-Count}")
    private String[] exposedHeaders;
    
    @Value("${cors.allow-credentials:true}")
    private boolean allowCredentials;
    
    @Value("${cors.max-age:3600}")
    private long maxAge;
    
    /**
     * Global CORS configuration for all endpoints.
     * This provides application-wide CORS settings.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods(allowedMethods)
                .allowedHeaders(allowedHeaders)
                .exposedHeaders(exposedHeaders)
                .allowCredentials(allowCredentials)
                .maxAge(maxAge);
    }
    
    /**
     * CORS configuration source bean for Spring Security integration.
     * This ensures CORS is properly applied before security filters.
     * 
     * @return CorsConfigurationSource with enterprise security settings
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Set allowed origins from properties
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins));
        
        // Alternatively, for more dynamic control, use origin patterns
        // configuration.setAllowedOriginPatterns(Arrays.asList("https://*.example.com", "http://localhost:[*]"));
        
        // Set allowed HTTP methods
        configuration.setAllowedMethods(Arrays.asList(allowedMethods));
        
        // Set allowed headers (use specific headers in production)
        if ("*".equals(allowedHeaders)) {
            configuration.addAllowedHeader("*");
        } else {
            configuration.setAllowedHeaders(Arrays.asList(allowedHeaders.split(",")));
        }
        
        // Expose headers that clients can access
        configuration.setExposedHeaders(Arrays.asList(exposedHeaders));
        
        // Allow credentials (cookies, authorization headers)
        configuration.setAllowCredentials(allowCredentials);
        
        // Set preflight cache duration (in seconds)
        configuration.setMaxAge(maxAge);
        
        // Apply CORS configuration to all paths
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
    
    /**
     * Strict CORS configuration bean for production environments.
     * Use this for more restrictive CORS policies.
     * 
     * @return CorsConfigurationSource with strict security settings
     */
    @Bean("strictCorsConfigurationSource")
    public CorsConfigurationSource strictCorsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Explicitly set allowed origins (no wildcards)
        configuration.setAllowedOrigins(List.of(
                "https://app.example.com",
                "https://admin.example.com"
        ));
        
        // Limit to essential HTTP methods
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        
        // Specify exact headers instead of wildcard
        configuration.setAllowedHeaders(List.of(
                "Authorization",
                "Content-Type",
                "X-Requested-With",
                "Accept",
                "Origin"
        ));
        
        // Expose only necessary headers
        configuration.setExposedHeaders(List.of(
                "Authorization",
                "Content-Type"
        ));
        
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(1800L); // 30 minutes
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        
        return source;
    }
}
