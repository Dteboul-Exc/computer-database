package com.excilys.mapper;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;

/**
 * @author dteboul Mapper that transform a Company Object into a CompanyDTO
 *         object or vice versa
 */
@Component
public class CompanyMapper {

	private static final Logger LOG = LoggerFactory.getLogger(CompanyMapper.class);

	/**
	 * Convert a CompanyDTO into a Company
	 * 
	 * @param cDTO
	 * @return
	 */
	public  Optional<Company> companyDTOToCompany(CompanyDTO cDTO) {
		
		if (cDTO == null) 
			{
			LOG.error("DTO null so invalid");
			throw new IllegalArgumentException("null object in input");
			}
		LOG.debug("Starting converting DTOcompany id " + cDTO.getId() + " to company");
		String name = null;
		if (cDTO.getName() != null && !"".equals(cDTO.getName().trim())) {
			name = cDTO.getName();
		}
		long id = 0;
		if (cDTO.getId() != null && !"".equals(cDTO.getId())) {
			try {
				id = Long.parseLong(cDTO.getId());
			} catch (NumberFormatException e) {
				LOG.error("DTO identifier invalid");
				return Optional.empty();
			}
		}
		return Optional.ofNullable(new Company(id, name));
	}

	/**
	 * Convert a Company into a CompanyDTO
	 * 
	 * @param company
	 * @return
	 */
	public  Optional<CompanyDTO> companyToDTO(final Company company) {
		
		if (company == null) 
		{
		LOG.error("DTO null so invalid");
		throw new IllegalArgumentException("null object in input");
		}
		LOG.debug("Starting converting company to DTO");
		String id = company.getId() == 0 ? "" : String.valueOf(company.getId());
		String name = company.getName();
		CompanyDTO result = new CompanyDTO();
		result.setId(id);
		result.setName(name);
		return Optional.of(result);
	}

}
