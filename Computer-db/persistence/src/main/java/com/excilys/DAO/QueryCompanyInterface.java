package com.excilys.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.excilys.model.Company;

@Repository
public interface QueryCompanyInterface extends CrudRepository<Company, Long> {
}
