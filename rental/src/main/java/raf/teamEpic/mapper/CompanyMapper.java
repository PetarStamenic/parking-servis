package raf.teamEpic.mapper;

import org.springframework.stereotype.Component;
import raf.teamEpic.dto.company.CompanyDTO;
import raf.teamEpic.dto.company.CreateCompanyDTO;
import raf.teamEpic.models.Company;

@Component
public class CompanyMapper {

    public Company createCompanyDTOtoCompany(CreateCompanyDTO dto){
        Company company = new Company();
        company.setManagerId(dto.getManagerId());
        company.setManagerEmail(dto.getManagerEmail());
        company.setNumberOfCars(dto.getNumberOfCars());
        company.setCity(dto.getCity());
        company.setDescription(dto.getDescription());
        company.setCompanyName(dto.getName());
        return company;
    }

    public CompanyDTO companyToCompanyDTO(Company company){
        CompanyDTO dto = new CompanyDTO();
        dto.setCity(company.getCity());
        dto.setNumberOfCars(company.getNumberOfCars());
        dto.setDescription(company.getDescription());
        dto.setCompanyName(company.getCompanyName());
        return dto;
    }

}
