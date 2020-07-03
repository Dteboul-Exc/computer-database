package com.excilys.CrudRepository;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.excilys.model.Company;

@Repository
public interface QueryCompanyInterface extends  CrudRepository<Company,Long>{
}
