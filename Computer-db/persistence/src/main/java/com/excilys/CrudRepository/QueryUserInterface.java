package com.excilys.CrudRepository;

import org.springframework.data.repository.CrudRepository;

import com.excilys.model.User;

public interface QueryUserInterface extends CrudRepository<User,Long> {

}
