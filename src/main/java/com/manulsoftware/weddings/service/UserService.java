package com.manulsoftware.weddings.service;

import com.manulsoftware.weddings.entity.User;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public interface UserService extends Repository<User, Integer> {

	/**
	 * Finds the user with the specified username
	 * 
	 * @return
	 * @throws SQLException
	 */
	public User findOneByUsername(final String username);

	/**
	 * Saves the changes to the user
	 * 
	 * @param user
	 * @return
	 */
	public User save(final User user);
}
