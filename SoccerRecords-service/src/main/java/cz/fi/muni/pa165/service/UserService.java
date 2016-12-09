package cz.fi.muni.pa165.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.entity.User;

public interface UserService {

	/**
	 * Register the given user with the given unencrypted password.
	 */
	void registerUser(User u, String unencryptedPassword);

	/**
	 * Get all registered users
	 */
	List<User> getAllUsers();

	/**
	 * Try to authenticate a user. Return true only if the hashed password matches the records.
	 */
	boolean authenticate(User u, String password);

	/**
	 * Check if the given user is admin.
	 */
	boolean isAdmin(User u);

	User findUserById(Long userId);

	User findUserByEmail(String email);
}
