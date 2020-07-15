package com.excilys.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.excilys.model.User;

@Repository
public interface QueryUserInterface extends CrudRepository<User, Long> {

}
