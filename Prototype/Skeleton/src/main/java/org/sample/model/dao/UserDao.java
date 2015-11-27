package org.sample.model.dao;

import org.sample.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Team7
 *
 */
public interface UserDao extends CrudRepository<User,Long> {
	
	public User findByEmail(String email);
	
}
