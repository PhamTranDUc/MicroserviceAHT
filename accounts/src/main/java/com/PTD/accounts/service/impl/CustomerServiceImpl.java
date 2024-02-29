package com.PTD.accounts.service.impl;

import com.PTD.accounts.dto.*;
import com.PTD.accounts.entity.Accounts;
import com.PTD.accounts.entity.Customer;
import com.PTD.accounts.mapper.AccountsMapper;
import com.PTD.accounts.mapper.CustomerMapper;
import com.PTD.accounts.repository.AccountRepository;
import com.PTD.accounts.repository.CustomerRepository;
import com.PTD.accounts.service.ICustomerService;
import com.PTD.accounts.service.client.CardsFeignClient;
import com.PTD.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;
    private AccountRepository accountRepository;
    @Override
    public CustomerDetailDto fetchCustomerDetail(String mobileNumber) {
        Customer customer= customerRepository.findByMobileNumber(mobileNumber).get();
        Accounts accounts= accountRepository.findByCustomerId(customer.getId()).get();

        CustomerDetailDto customerDetailDto= CustomerMapper.mapToCustomerDetailDto(new CustomerDetailDto(),customer);
        customerDetailDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));

        ResponseEntity<LoansDto> loansDto= loansFeignClient.fetchLoanDetails(mobileNumber);
        ResponseEntity<CardsDto> cardDto= cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailDto.setLoansDto(loansDto.getBody());
        customerDetailDto.setCardsDto(cardDto.getBody());
        return customerDetailDto;
    }
}
