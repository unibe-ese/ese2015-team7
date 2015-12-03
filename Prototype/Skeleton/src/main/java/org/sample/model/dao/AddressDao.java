package org.sample.model.dao;

import org.sample.model.Address;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>This is the interface between the database and the services concerning Addresses.</p>
 * It provides basic methods as storing, deleting, updating and getting entries in the database.
 * 
 * @author Team7
 *
 */
public interface AddressDao  extends CrudRepository<Address,Long>{

}
