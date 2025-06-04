package com.example.demo.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class ConfigSeguranca {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(requests -> {
                    requests.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll();

                    requests.requestMatchers(HttpMethod.GET, "/produtos", "/produtos/{id}")
                            .hasAnyRole("CLIENTE", "FUNCIONARIO", "GERENTE");
                    requests.requestMatchers(HttpMethod.GET, "/clientes/{id}")
                            .hasAnyRole("CLIENTE", "FUNCIONARIO", "GERENTE");
                            
                    requests.requestMatchers("/produtos/**")
                            .hasAnyRole("FUNCIONARIO", "GERENTE");
                    requests.requestMatchers("/clientes/**")
                            .hasAnyRole("FUNCIONARIO", "GERENTE");

                    requests.requestMatchers("/cupons/**", "/enderecos/**", "/categorias/**",
                            "/fidelidade/**", "/historico-precos/**")
                            .hasAnyRole("FUNCIONARIO", "GERENTE");
                    requests.requestMatchers("/pedidos/**", "/wishlists/**")
                            .hasAnyRole("CLIENTE", "FUNCIONARIO", "GERENTE");
                    requests.requestMatchers("/**").hasRole("GERENTE");
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilter(new JwtAutenticationFilter(
                authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil));
        http.addFilter(new JwtAuthorizationFilter(
                authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)),
                jwtUtil, userDetailsService));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:8080/"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",
                corsConfiguration.applyPermitDefaultValues());
        return source;
    }
}
