package com.excilys.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;



@Repository
public interface QueryComputerInterface extends  CrudRepository<Computer,Long>{
	
	//List<Computer> findBy(Pageable pageable);
	//List<Computer> findByNameContainingIgnoreCase(String name);

}