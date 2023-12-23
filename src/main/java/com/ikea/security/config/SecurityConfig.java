package com.ikea.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
        // 익명 사용자만 접근 가능한 페이지
        .antMatchers(
            "/sign/in",
            "/sign/up",
            "/user/find-username",
            "/user/find-password").anonymous()
        // 인증된 사용자만 접근 가능한 페이지
        .antMatchers(
            "/user/profile").authenticated()
        // 관리자만 접근 가능한 페이지
        .antMatchers(
            "/category/main/register",
            "/category/main/list",
            "/category/main/modify/{id:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}",
            "/category/sub/register",
            "/category/sub/modify/{id:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}",
            "/product/register",
            "/product/modify/{id:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}",
            "/product-option/register").hasRole("ADMIN")
        // 모두 사용가능한 API
        .antMatchers(HttpMethod.GET, "/api/product/{id:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}").permitAll()
        // 익명 사용자만 접근 가능한 API
        .antMatchers(
            "/api/sign/up",
            "/api/user/find-username",
            "/api/user/find-password").anonymous()
        // 인증된 사용자만 접근 가능한 API
        .antMatchers(
            "/api/user/me",
            "/api/user/{id:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}",
            "/api/user/change-password",
            "/api/product-review",
            "/api/product-review/{id:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}").authenticated()
        // 관리자만 접근 가능한 API
        .antMatchers(
            "/api/main-category",
            "/api/main-category/{id:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}",
            "/api/sub-category",
            "/api/sub-category/{id:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}",
            "/api/product",
            "/api/product/{id:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}",
            "/api/product-option",
            "/api/product-option/{id:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}").hasRole("ADMIN")
        .anyRequest().permitAll()
      .and()
        // form login config
        .formLogin()
        .loginPage("/sign/in")
        .loginProcessingUrl("/sign/in")
        .successHandler(successHandler)
        .failureHandler(failureHandler)
      .and()
        // logout config
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
      .and()
        // session config
        .sessionManagement()
        .maximumSessions(1)
        .maxSessionsPreventsLogin(true);
    http
        // remember me config
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
