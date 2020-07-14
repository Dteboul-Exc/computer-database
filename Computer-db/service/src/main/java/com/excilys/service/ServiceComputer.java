package com.excilys.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.excilys.DAO.QueryComputerInterface;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;

/**
 * @author dteboul Assure the link between the servlet and the DAO.
 */
@Service
public class ServiceComputer {
	private static final Logger lOG = LoggerFactory.getLogger(ServiceComputer.class);
	@Autowired
	QueryComputerInterface repo;

	
	@Autowired
	ComputerMapper ComputerMapper;
	public ServiceComputer() {
	}

	public ServiceComputer(QueryComputerInterface c) {
		this.repo = c;
	}

	public void set_ComputerValidator(QueryComputerInterface DAO) {
		this.repo = DAO;
	}

	public int getCountComputer() {
		lOG.debug("Getting rowcount of the computer db");
		return (int) repo.count();
	}

	public List<ComputerDTO> getAllComputer(int offset, int limit) {

		lOG.debug("getAllComputer start");
		Pageable page = PageRequest.of(offset, limit);
		List<Computer> result = repo.findAll(page);
		return result.stream().map(i -> ComputerMapper.computerToDTO(i).get()).distinct().collect(Collectors.toList());
	}

	public Optional<ComputerDTO> getSpecificComputer(int id) {

		lOG.debug("getSpecificComputer start using computer id : " + id);
		Computer result = repo.findById((long) id).get();
		return ComputerMapper.computerToDTO(result);
	}

	public List<ComputerDTO> Search_Computer(String name) {
		lOG.debug("Search_Computer start using computer name : " + name);
		List<Computer> result = repo.findByNameContainingIgnoreCase(name);
		return result.stream().map(i -> ComputerMapper.computerToDTO(i).get()).distinct().collect(Collectors.toList());
	}

	public int deleteSpecificComputer(int id) {

		lOG.debug("getSpecificComputer start using computer id : " + id);

		repo.deleteById((long) id);
		return 1;
	}

	public ComputerDTO addComputer(ComputerDTO computer) {
		lOG.debug("addComputer start using computer");
		Computer save = repo.save(ComputerMapper.CDTOToComputer(computer).get());
		return ComputerMapper.computerToDTO(save).get();
	}

	public int updateComputer(ComputerDTO computer) {
		lOG.debug("updateComputer start using computer");
		repo.save(ComputerMapper.CDTOToComputer(computer).get());
		return 1;
	}

	public List<ComputerDTO> getAllComputerOrderBy(String state, int offset, int limit) {

		lOG.debug("getAllComputer start");
		Pageable page = PageRequest.of(offset, limit, Direction.ASC, state);
		List<Computer> result = repo.findAll(page);
		return result.stream().map(i -> ComputerMapper.computerToDTO(i).get()).distinct().collect(Collectors.toList());
	}

}
