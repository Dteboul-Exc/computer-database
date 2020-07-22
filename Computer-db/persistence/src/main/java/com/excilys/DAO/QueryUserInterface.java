package com.excilys.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.excilys.model.UserDB;


@Repository
public interface QueryUserInterface extends CrudRepository<UserDB, Long> {

}
