package com.manulsoftware.weddings.service.impl;

import com.manulsoftware.weddings.entity.User;
import com.manulsoftware.weddings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User user = userService.findOneByUsername(username);
		if (!user.isEncrypted()) {
			if (user.getPassword() != null) {
				final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				user.setEncrypted(true);
				userService.save(user);
				return user;
			}
			return null;
		} else {
			return user;
		}
	}
}
