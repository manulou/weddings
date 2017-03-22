package com.manulsoftware.weddings.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	NoRedirectLogoutHandler logoutHandler;

	protected void configure(HttpSecurity http) throws Exception {
		http
			.logout()
			.logoutUrl("/secure/user/logout")
			.logoutSuccessHandler(logoutHandler)
			.and()
			.authorizeRequests()
			.antMatchers("/secure/**").authenticated()
			.antMatchers("/**").permitAll()
			.and().httpBasic()
			.authenticationEntryPoint((request, response, authException) -> {
//                String requestedWith = request.getHeader("X-Requested-With");
//                if ("XMLHttpRequest".equals(requestedWith)) {
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.addHeader("WWW-Authenticate", "Application driven");
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//                } else {
//                    HttpServletResponse httpResponse = (HttpServletResponse) response;
//                    httpResponse.addHeader("WWW-Authenticate", "Basic realm=Realm");
//                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//                }
            }).and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class).csrf().csrfTokenRepository(csrfTokenRepository());
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

	@Bean
	NoRedirectLogoutHandler noRedirect() {
		return new NoRedirectLogoutHandler();
	}
}
