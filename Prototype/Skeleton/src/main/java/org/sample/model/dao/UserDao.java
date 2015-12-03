package org.sample.model.dao;

import org.sample.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>This is the interface between the database and the services concerning Users.</p>
 * It provides basic methods as storing, deleting, updating and getting entries in the database.
 * 
 * @author Team7
 *
 */
public interface UserDao extends CrudRepository<User,Long> {
	
	public User findByEmail(String email);
	
}
