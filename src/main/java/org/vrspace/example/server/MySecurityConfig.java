package org.vrspace.example.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

  @Bean
  SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated());
    http.formLogin(form -> form.failureUrl("/login?error").defaultSuccessUrl("/").loginPage("/login").permitAll());
    http.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
        .permitAll());
    return http.build();
  }

  @Bean
  InMemoryUserDetailsManager configureUsers() throws Exception {
    UserDetails user1 = User.withUsername("user1").password(passwordEncoder().encode("pass1")).roles("USER").build();
    UserDetails user2 = User.withUsername("user2").password(passwordEncoder().encode("pass2")).roles("USER").build();
    return new InMemoryUserDetailsManager(user1, user2);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
