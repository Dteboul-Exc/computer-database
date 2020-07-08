package com.excilys.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.CrudRepository.QueryCompanyInterface;
import com.excilys.CrudRepository.QueryUserInterface;
import com.excilys.dto.UserDTO;
import com.excilys.mapper.UserMapper;
import com.excilys.model.User;

@Service
public class ServiceUser {
	@Autowired
	private QueryUserInterface repo;

	public void set_DAOCompany(QueryUserInterface DAO) {
		this.repo = DAO;
	}

	public List<UserDTO> getAllCompany() {
		Logger logger = LoggerFactory.getLogger(ServiceUser.class);
		logger.debug("getAllCompany start");
		List<User> result = (List<User>) repo.findAll();
		return result.stream().map(i -> UserMapper.UserToDTO(i).get()).distinct().collect(Collectors.toList());
	}

	public Optional<UserDTO> getSpecificCompany(int id) {
		Logger logger = LoggerFactory.getLogger(QueryCompanyInterface.class);
		logger.debug("get a specific Company start");
		User result = repo.findById((long) id).get();
		return UserMapper.UserToDTO(result);
	}

	/**
	 * Used for mockito testing
	 * 
	 * @param tDAOCompany
	 */
	public void setDAOCompany(final QueryUserInterface tDAOCompany) {
		this.repo = tDAOCompany;
	}

	public int deleteUser(int id) {
		repo.deleteById((long) id);
		return 1;
	}
}
