package com.gal.coupons.logic;

import com.gal.coupons.dal.ICompaniesDal;
import com.gal.coupons.dto.CompanyDTO;
import com.gal.coupons.entities.Company;
import com.gal.coupons.enums.ErrorType;
import com.gal.coupons.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompaniesLogic {

    private ICompaniesDal companiesDal;

    @Autowired
    private CouponsLogic couponsLogic;

    @Autowired
    public CompaniesLogic(ICompaniesDal companiesDal) {
        this.companiesDal = companiesDal;
    }


    public void createCompany(Company company) throws ServerException {
        validateCompanyName(company.getName());
        validateCompanyAddress(company.getAddress());
        validateCompanyPhoneNumber(company.getPhoneNumber());
        try {
            companiesDal.save(company);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }

    }

    public CompanyDTO getCompanyById(int companyId) throws ServerException {
        validateCompanyExistenceByID(companyId);
        try {
            return companiesDal.findCompanyById(companyId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public CompanyDTO getCompanyByName(String companyName) throws ServerException {
        validateCompanyExistenceByName(companyName);
        try {
            return companiesDal.findCompanyByName(companyName);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }

    }

    public List<CompanyDTO> getAllCompanies() throws ServerException {
        try {
            return companiesDal.findAllCompanies();
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public void updateCompany(Company company) throws ServerException {
        validateCompanyName(company.getName());
        validateCompanyAddress(company.getAddress());
        validateCompanyPhoneNumber(company.getPhoneNumber());
        try {
            companiesDal.save(company);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }

    }

    public void removeCompany(int companyId) throws ServerException {
        validateCompanyExistenceByID(companyId);
        try {
            companiesDal.deleteById(companyId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    void validateCompanyExistenceByID(int companyId) throws ServerException {
        if (!companiesDal.existsById(companyId)) {
            throw new ServerException(ErrorType.COMPANY_DOES_NOT_EXIST);
        }
    }

    void validateCompanyExistenceByName(String companyName) throws ServerException {
        if (companiesDal.findCompanyByName(companyName) == null) {
            throw new ServerException(ErrorType.COMPANY_DOES_NOT_EXIST);
        }
    }

    private void validateCompanyName(String companyName) throws ServerException {
        if (companyName.length() < 3) {
            throw new ServerException(ErrorType.INVALID_COMPANY_NAME);
        }

        if (companyName.length() > 25) {
            throw new ServerException(ErrorType.INVALID_COMPANY_NAME);
        }
    }

    private void validateCompanyAddress(String address) throws ServerException {
        if (address.length() < 6) {
            throw new ServerException(ErrorType.INVALID_ADDRESS);
        }

        if (address.length() > 30) {
            throw new ServerException(ErrorType.INVALID_ADDRESS);
        }
    }

    private void validateCompanyPhoneNumber(String phoneNumber) throws ServerException {
        if (phoneNumber.length() < 5 || phoneNumber.length() > 12) {
            throw new ServerException(ErrorType.INVALID_PHONE_NUMBER_LENGTH);
        }
        int counterOfAsterisks = 0;
        int counterOfHyphens = 0;
        for (int index = 0; index < phoneNumber.length(); index++) {
            if (phoneNumber.charAt(index) == '*') {
                counterOfAsterisks++;
            }
            if (phoneNumber.charAt(index) == '-') {
                counterOfHyphens++;
            }
            if (phoneNumber.charAt(index) < '0' && phoneNumber.charAt(index) > '9' && phoneNumber.charAt(index) != '*'
                    && phoneNumber.charAt(index) != '-') {
                throw new ServerException(ErrorType.INVALID_COMPANY_PHONE_NUMBER);
            }
            if (counterOfAsterisks > 1) {
                throw new ServerException(ErrorType.INVALID_COMPANY_PHONE_NUMBER);
            }
            if (counterOfHyphens > 2) {
                throw new ServerException(ErrorType.INVALID_COMPANY_PHONE_NUMBER);
            }
        }
    }
}
