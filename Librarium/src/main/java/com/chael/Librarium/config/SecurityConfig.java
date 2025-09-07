package com.chael.Librarium.config;

import com.chael.Librarium.services.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.chael.Librarium.entities.enums.Permission.*;
import static com.chael.Librarium.entities.enums.Role.ADMIN;
import static com.chael.Librarium.entities.enums.Role.LIBRARIAN;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final LogoutService logoutService;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthFilter jwtAuthFilter
    ) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // Disables CSRF protection (needed for stateless APIs with JWT).
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api/auth/**").permitAll()


//                                .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), LIBRARIAN.name())

                                .requestMatchers(PATCH, "/api/authors/patch-author").hasAnyAuthority(LIBRARIAN_UPDATE.name(), ADMIN_UPDATE.name())
                                .requestMatchers(DELETE, "/api/authors/delete-author/{id}").hasAnyAuthority(LIBRARIAN_DELETE.name(), ADMIN_DELETE.name())

                                .requestMatchers(PATCH, "/api/books/patch-book/{id}").hasAnyAuthority(LIBRARIAN_UPDATE.name(), ADMIN_UPDATE.name())
                                .requestMatchers(PATCH, "/api/book/patch-book/{id}/{authorId}").hasAnyAuthority(LIBRARIAN_UPDATE.name(), ADMIN_UPDATE.name())
                                .requestMatchers(DELETE, "/api/books/delete-book/{id}").hasAnyAuthority(ADMIN_DELETE.name(), LIBRARIAN_DELETE.name())

                                .requestMatchers(PATCH, "/api/borrow-records/patch-record-due/").hasAnyAuthority(ADMIN_UPDATE.name(), LIBRARIAN_UPDATE.name())

                                .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(LIBRARIAN_CREATE.name(), ADMIN_CREATE.name())
                                .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(LIBRARIAN_UPDATE.name(), ADMIN_UPDATE.name())
                                .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(LIBRARIAN_DELETE.name(), ADMIN_DELETE.name())


//                        .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
//
//                        .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
//                        .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
//                        .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
//                        .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())
//                        .anyRequest().authenticated()
                )
                // Starts configuring authorization.MANAG
                // Right now it matches nothing, so no endpoint is public
                // Means whatever is matched (currently nothing) is open.
                // Means whatever is matched (currently nothing) is open.
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // No server-side sessions, pure JWT-based auth.
                .authenticationProvider(authenticationProvider)
                // Uses your custom AuthenticationProvider
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                // Injects your custom JWT filter before Spring’s built-in username-password filter.
                .logout(logout -> logout
                        .logoutUrl("/api/v1/auth/logout")
                        .addLogoutHandler(logoutService)
                        .logoutSuccessHandler((request, response, authentication) -> {
                            SecurityContextHolder.clearContext();
                        })
                );
        return http.build();
    }
}
