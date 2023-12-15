package com.ikea.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final AuthenticationSuccessHandler successHandler;
  private final AuthenticationFailureHandler failureHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .cors().disable()
        .authorizeRequests()
        // 페이지 인가
        .antMatchers(
            "/sign/in",
            "/sign/up",
            "/user/find-username",
            "/user/find-password").anonymous()
        .antMatchers("/user/profile").authenticated()
        .antMatchers(
            "/category/main/register",
            "/category/sub/register",
            "/product/register",
            "/product-option/register").hasRole("ADMIN")
        // API 인가
        .antMatchers(
            "/api/sign/up",
            "/api/user/find-username",
            "/api/user/find-password").anonymous()
        .antMatchers(
            "/api/user/me",
            "/api/user/{id}").authenticated()
        .antMatchers(
            "/api/main-category/register",
            "/api/main-category/{id}",
            "/api/sub-category/register",
            "/api/sub-category/{id}",
            "/api/product",
            "/api/product/{id}",
            "/api/product-option",
            "/api/product-option/{id}").hasRole("ADMIN")
        .anyRequest().permitAll()
        .and()
        .formLogin()
        .loginPage("/sign/in")
        .loginProcessingUrl("/sign/in")
        .successHandler(successHandler)
        .failureHandler(failureHandler)
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .and()
        .sessionManagement()
        .maximumSessions(1)
        .maxSessionsPreventsLogin(true);
    http
        .rememberMe()
        .rememberMeParameter("remember")
        .tokenValiditySeconds(60*60)
        .alwaysRemember(false)
        .userDetailsService(userDetailsService);
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
