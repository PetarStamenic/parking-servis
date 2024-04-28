package raf.teamEpic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.teamEpic.models.Company;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> findCompanyByCompanyNameAndCity(String name,String city);
    List<Company> findCompaniesByCompanyName(String name);
    List<Company> findCompaniesByCity(String city);
    Optional<Company> findCompanyByManagerId(Long id);
}
