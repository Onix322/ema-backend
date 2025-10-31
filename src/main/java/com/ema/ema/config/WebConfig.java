package com.ema.ema.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final List<HttpMethod> notAllowedHttpMethods = List.of(
            // INSERT forbidden HttpMethods
    );

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] allowedMethods = Arrays.stream(HttpMethod.values())
                .filter(m -> !this.notAllowedHttpMethods.contains(m))
                .map(HttpMethod::toString)
                .toArray(String[]::new);

        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods(allowedMethods);
    }
}
