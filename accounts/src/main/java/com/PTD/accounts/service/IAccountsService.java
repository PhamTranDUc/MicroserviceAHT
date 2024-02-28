package com.PTD.accounts.service;

import com.PTD.accounts.dto.CustomerDto;

public interface IAccountsService {
    void createAccounts(CustomerDto customerDto);
    CustomerDto fetchAccounts(String mobileNumber);
    boolean updateAccounts(CustomerDto customerDto);
    boolean deleteByMobileNumber(String mobileNumber);
}
