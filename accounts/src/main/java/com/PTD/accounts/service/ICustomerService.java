package com.PTD.accounts.service;

import com.PTD.accounts.dto.CustomerDetailDto;
import com.PTD.accounts.dto.CustomerDto;

public interface ICustomerService {

    CustomerDetailDto fetchCustomerDetail(String mobileNumber);
}
