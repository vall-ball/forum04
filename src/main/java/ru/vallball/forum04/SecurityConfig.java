package ru.vallball.forum04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/users/change_user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/for_users/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                        .requestMatchers("/users/**").permitAll()
                        .requestMatchers("/forum/category/**").hasAnyRole("ADMIN", "MODERATOR")
                        .requestMatchers("/forum/section/**").hasAnyRole("ADMIN", "MODERATOR")
                        .requestMatchers(HttpMethod.POST, "/forum/topic/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/forum/topic/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/forum/topic/**").hasAnyRole("ADMIN", "MODERATOR")
                        .requestMatchers(HttpMethod.POST, "/forum/topic/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/forum/topic/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/forum/topic/**").authenticated()
                        .requestMatchers("/forum/message/**").authenticated()
                        .requestMatchers("/forum").permitAll()
                );

        return http.build();
    }
}
