package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Отключаем CSRF для API
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/news/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.POST, "/api/news/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.PUT, "/api/news/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.DELETE, "/api/news/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.GET, "/api/categories/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.POST, "/api/categories/**").hasAnyRole("ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.PUT, "/api/categories/**").hasAnyRole("ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.DELETE, "/api/categories/**").hasAnyRole("ADMIN", "MODERATOR")
                .antMatchers("/api/users/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return username -> {
            // Пример аутентификации: запрос данных пользователя из базы данных
            // В реальной ситуации используйте UserService, который будет работать с вашей базой данных
            if ("admin".equals(username)) {
                return User.withUsername("admin")
                        .password(passwordEncoder().encode("admin123"))
                        .roles("ADMIN")
                        .build();
            } else if ("user".equals(username)) {
                return User.withUsername("user")
                        .password(passwordEncoder().encode("user123"))
                        .roles("USER")
                        .build();
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
