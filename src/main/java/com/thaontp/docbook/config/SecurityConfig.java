package com.thaontp.docbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// private UserDetailsServiceImp myAppUserDetailsService;
	//
	// @Autowired
	// public SecurityConfig(UserDetailsServiceImp myAppUserDetailsService) {
	// this.myAppUserDetailsService = myAppUserDetailsService;
	// }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    .antMatchers("/bookDoctor/index", "/bookDoctor/register", "/bookDoctor/checkout",
		                 "/bookDoctor/single-product", "/bookDoctor/cart")
		    .permitAll()
		    // .antMatchers("/bookDoctor/shipper").permitAll()
		    .antMatchers("/bookDoctor/merchant/**").hasAnyRole("MERCHANT")
		    .antMatchers("/bookDoctor/shipper/**").hasAnyRole("SHIPPER").anyRequest()
		    .authenticated().and().formLogin()  // login
		                                        // configuration
		    .loginPage("/bookDoctor/login").permitAll().loginProcessingUrl("/app-login")
		    .usernameParameter("app_username").passwordParameter("app_password")
		    .defaultSuccessUrl("/bookDoctor/default").and().logout()    // logout configuration
		    .logoutUrl("/app-logout").logoutSuccessUrl("/bookDoctor/login").and()
		    .exceptionHandling() // exception handling configuration
		    .accessDeniedPage("/bookDoctor/error");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// auth.userDetailsService(myAppUserDetailsService).passwordEncoder(passwordEncoder);
		auth.inMemoryAuthentication().withUser("shipper").password("m123").roles("SHIPPER").and()
		    .withUser("merchant").password("t123").roles("MERCHANT");
	}
}
