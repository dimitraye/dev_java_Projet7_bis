package com.nnk.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  //TODO : ne pas oublier de changer la config tempo

  //Config temporaire
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .anyRequest()
        .permitAll()
        /*.antMatchers("/admin").hasRole("ADMIN")
        .antMatchers("/user").hasRole("USER")
        .anyRequest().authenticated()*/
        .and()
        .formLogin();
  }
}
