package com.example.springboot.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final AccessDeniedHandler accessDeniedHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication()
		        .withUser("user")
		        .password(encoder.encode("password"))
		        .roles("USER")
		        .and()
		        .withUser("admin")
		        .password(encoder.encode("admin"))
		        .roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers()
		        .frameOptions()
		        .disable()
		        .and()
		        .csrf()
		        .ignoringAntMatchers("/h2-console/**")
		        .disable()
		        .authorizeRequests()
		        .antMatchers("/sign-up/**", "/users/**", "/h2-console/**", "/resources/**", "/403")
		        .permitAll()
		        .antMatchers("/home")
		        .hasRole("ADMIN")
		        .anyRequest()
		        .authenticated()
		        .and()
		        .formLogin()
		        .permitAll()
		        .and()
		        .logout()
		        .permitAll()
		        .and()
		        .exceptionHandling()
		        .accessDeniedHandler(accessDeniedHandler);
	}
}
