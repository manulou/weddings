package com.manulsoftware.weddings.web.config;


import com.manulsoftware.weddings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(encoder());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return (username) -> {
            UserDetails account = userService.findOneByUsername(username);
            if(account != null) {
                return account;
            } else {
                throw new UsernameNotFoundException("could not find the user '" + username + "'");
            }
        };
    }



    @Bean
    BCryptPasswordEncoder encoder() {
      return new BCryptPasswordEncoder();
    }
}
