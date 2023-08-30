package com.nnk.springboot.config;

import com.nnk.springboot.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  private final UserDetailsServiceImpl userDetailsServiceImpl;

  private final LoginSuccessHandler loginSuccessHandler;


  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      //Accès à H2
      .csrf(csrf -> csrf.ignoringAntMatchers("/h2-console/**"))
            //Auhorisations d'accès aux requêtes
            .authorizeRequests( auth -> auth
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/").permitAll()// acces à la page avant login
            .antMatchers("/user/*").hasRole("ADMIN")// acces au formulaire de signup
            .antMatchers("/app/login").permitAll()
            // permet l'acces aux ressources static se trouvant dans le dossier resources de l'application
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            //Any other request need to be authentified
            .anyRequest().authenticated()
        )
        //Gestion de l'authentification de user JPA Spring security
        .userDetailsService(userDetailsServiceImpl)
        .headers(headers -> headers.frameOptions().sameOrigin())
        //Authentification par formulaire
        .formLogin()
        .loginPage("/app/login")
        .loginProcessingUrl("/login")
        .successHandler(loginSuccessHandler)
        .permitAll()
        .and()
        .logout()
        .logoutUrl("/logout")
        .and()
        .oauth2Login()
        .loginPage("/app/login")
        .defaultSuccessUrl("/bidList/list")
    ;
    return http.build();
  }

  /**
   * Permet de cripter le mot de passe
   * */
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}