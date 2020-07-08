package com.excilys.mapper;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dto.UserDTO;
import com.excilys.model.User;

public class UserMapper {

	private static final Logger LOG = LoggerFactory.getLogger(CompanyMapper.class);

	public static Optional<UserDTO> UserToDTO(User user) {
		LOG.debug("Starting converting User id " + user.getId() + " to user DTO");
		return Optional.of(UserDTO.Builder
				.newInstance(Long.toString(user.getId()), user.getUsername(), user.getPassword(), user.getRole())
				.build());

	}

	public static Optional<User> DTOToUser(UserDTO user) {
		LOG.debug("Starting converting UserDTO id " + user.getId() + " to user ");
		return Optional.of(User.Builder
				.newInstance(Long.parseLong(user.getId()), user.getUsername(), user.getPassword(), user.getRole())
				.build());
	}
}
