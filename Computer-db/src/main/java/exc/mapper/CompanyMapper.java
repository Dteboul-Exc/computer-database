package main.java.exc.mapper;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import main.java.exc.dto.CompanyDTO;
import main.java.exc.model.Company;

public class CompanyMapper {

    private static final Logger LOG = LoggerFactory.getLogger(CompanyMapper.class);

    
    public static Optional<Company> companyDTOToCompany(CompanyDTO cDTO) {
    	LOG.debug("Starting converting DTOcompany id " + cDTO.getId() +" to company");
        if (cDTO == null) {
            return Optional.empty();
        }
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


    public static CompanyDTO companyToDTO(final Company company) {
    	LOG.debug("Starting converting company id " + company.getId() +" to DTO");
        String id = company.getId() == 0 ? "" : String.valueOf(company.getId());
        String name = company.getName();
        CompanyDTO result = new CompanyDTO();
        result.setId(id);
        result.setName(name);
        return result;
    }


}
