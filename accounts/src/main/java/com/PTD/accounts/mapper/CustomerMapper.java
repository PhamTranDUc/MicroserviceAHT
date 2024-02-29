package com.PTD.accounts.mapper;

import com.PTD.accounts.dto.CustomerDetailDto;
import com.PTD.accounts.dto.CustomerDto;
import com.PTD.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto){
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer){
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        return customer;
    }

    public static CustomerDetailDto mapToCustomerDetailDto(CustomerDetailDto customerDetailDto, Customer customer){
        customerDetailDto.setMobileNumber(customer.getMobileNumber());
        customerDetailDto.setName(customer.getName());
        customerDetailDto.setEmail(customer.getEmail());
        return customerDetailDto;
    }

}
