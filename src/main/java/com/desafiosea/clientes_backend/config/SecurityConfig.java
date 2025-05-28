package com.desafiosea.clientes_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
          .inMemoryAuthentication()
            .withUser("admin")
              .password("{noop}123qwe!@#")
              .roles("ADMIN")
            .and()
            .withUser("usuario")
              .password("{noop}123qwe123")
              .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .cors().and()
          .csrf().disable()
          .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/clientes/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/clientes/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/clientes/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/clientes/**").hasAnyRole("ADMIN", "USER")
            .anyRequest().authenticated()
          .and()
            .httpBasic();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true); 
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}