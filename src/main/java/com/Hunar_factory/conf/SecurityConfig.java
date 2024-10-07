package com.Hunar_factory.conf;

import com.Hunar_factory.enums.Role;
import com.Hunar_factory.exceptions.JwtAccessDeniedHandler;
import com.Hunar_factory.exceptions.JwtAuthenticationEntryPoint;
import com.Hunar_factory.jwt.JWTAuthenticationFilter;
import com.Hunar_factory.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static com.Hunar_factory.constants.SecurityConstants.*;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)  // Enable method-level security
public class SecurityConfig {

    public final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    public final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final AuthenticationProvider authenticationProvider;
    public final JwtTokenProvider jwtTokenProvider;
    public final UserDetailsService userDetailsService;
    public final JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize->{
                    authorize.requestMatchers(PUBLIC_URL).permitAll()
                            .requestMatchers("/error").permitAll()
                            .requestMatchers("/api/v1/user/profile").authenticated()
                            .anyRequest().authenticated();
                })
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Maintain statelessness
                )
                .authenticationProvider(authenticationProvider) // Configure your authentication provider
                .exceptionHandling(ex->{
                    ex.accessDeniedHandler(jwtAccessDeniedHandler);
                    ex.authenticationEntryPoint(jwtAuthenticationEntryPoint);
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }









}
