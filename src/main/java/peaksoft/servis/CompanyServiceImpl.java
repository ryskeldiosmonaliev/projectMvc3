package peaksoft.servis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CompanyDao;
import peaksoft.model.Company;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyDao
            companyDAO;

    @Autowired
    public CompanyServiceImpl(CompanyDao companyDAO) {
        this.companyDAO = companyDAO;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyDAO.getAllCompanies();
    }

    @Override
    public void addCompany(Company company) {
        companyDAO.addCompany(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyDAO.getCompanyById(id);
    }

    @Override
    public void updateCompany(Company company) {
        companyDAO.updateCompany(company);
    }

    @Override
    public void deleteCompany(Company company) {
        companyDAO.deleteCompany(company);
    }
}
