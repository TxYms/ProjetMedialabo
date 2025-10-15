package com.medialabo.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  // API JSON -> Basic Auth (stateless)
	@Bean
	@Order(1)
	SecurityFilterChain api(HttpSecurity http) throws Exception {
	  http
	    .securityMatcher("/api/**", "/patients/**", "/notes/**")  // <— ajouté
	    .csrf(csrf -> csrf.disable())
	    .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
	    .httpBasic(Customizer.withDefaults())
	    .formLogin(form -> form.disable())
	    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	    .exceptionHandling(e -> e.authenticationEntryPoint((req, res, ex) -> {
	      res.addHeader("WWW-Authenticate", "Basic realm=\"medilabo-notes\"");
	      res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	    }));
	  return http.build();
	}

  // UI + actuator -> /actuator/**, /login et assets publics
  @Bean
  @Order(2)
  SecurityFilterChain ui(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(auth -> auth
        .requestMatchers(
          "/actuator/**",
          "/login",
          "/css/**", "/js/**", "/img/**"
        ).permitAll()
        .anyRequest().authenticated()
      )
      .formLogin(form -> form.permitAll());
    return http.build();
  }

  @Bean
  PasswordEncoder encoder() { return new BCryptPasswordEncoder(); }

  @Bean
  UserDetailsService users(PasswordEncoder enc) {
    UserDetails u = User.withUsername("organizer")
      .password(enc.encode("changeit"))
      .roles("USER")
      .build();
    return new InMemoryUserDetailsManager(u);
  }
}