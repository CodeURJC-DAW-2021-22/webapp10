package com.youdemy.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	RepositoryUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/courses/delete/**").permitAll();
        http.authorizeRequests().antMatchers("/courses").permitAll();
        http.authorizeRequests().antMatchers("/courses/page").permitAll();
        http.authorizeRequests().antMatchers("/courses/thumbnail/**").permitAll();
       

        // User pages
        http.authorizeRequests().antMatchers("/courses/new").hasAnyRole("TEACHER", "ADMIN");
        http.authorizeRequests().antMatchers("/image/**").hasAnyRole("TEACHER", "ADMIN");
        http.authorizeRequests().antMatchers("/orders/**").hasAnyRole("USER");

        // H2 Console access without csrf
        http.csrf().ignoringAntMatchers("/h2-console/**");
        http.headers().frameOptions().sameOrigin();

        // Sign in form
        http.formLogin().loginPage("/signin");
        http.formLogin().usernameParameter("userEmail");
        http.formLogin().passwordParameter("userPassword");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/signinError");
        
        // Sign out
        http.logout().logoutUrl("/signout");
        http.logout().logoutSuccessUrl("/");

        // Exception handling
        http.exceptionHandling().accessDeniedPage("/access-denied");
    }
}
