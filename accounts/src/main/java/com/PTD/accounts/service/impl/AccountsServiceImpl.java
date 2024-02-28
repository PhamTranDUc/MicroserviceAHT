package com.PTD.accounts.service.impl;

import com.PTD.accounts.constants.AccountsConstants;
import com.PTD.accounts.dto.AccountsDto;
import com.PTD.accounts.dto.CustomerDto;
import com.PTD.accounts.entity.Accounts;
import com.PTD.accounts.entity.Customer;
import com.PTD.accounts.exception.CustomerAlreadyExistsException;
import com.PTD.accounts.exception.ResourceNoFoundException;
import com.PTD.accounts.mapper.AccountsMapper;
import com.PTD.accounts.mapper.CustomerMapper;
import com.PTD.accounts.repository.AccountRepository;
import com.PTD.accounts.repository.CustomerRepository;
import com.PTD.accounts.service.IAccountsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccounts(CustomerDto customerDto) {
        Customer customer= CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer= customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Số điện thoại của bạn đã được đăng kí bởi một người khác: "+customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer customerNew= customerRepository.save(customer);

        Accounts accounts= createNewAccounts(customerNew);

        accountRepository.save(accounts);
    }

    @Override
    public CustomerDto fetchAccounts(String mobileNumber) {
        Customer customer= customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNoFoundException("Customer","mobileNumber",mobileNumber));
        Accounts accounts= accountRepository.findByCustomerId(customer.getId()).orElseThrow(()-> new ResourceNoFoundException("CustomerId","Customer_id",customer.getId().toString()));
        CustomerDto customerDto= CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        AccountsDto accountsDto= AccountsMapper.mapToAccountsDto(accounts,new AccountsDto());
        customerDto.setAccountsDto(accountsDto);
        return customerDto;
    }

    @Transactional
    @Override
    public boolean updateAccounts(CustomerDto customerDto) {
        boolean isUpdate=false;
        AccountsDto accountsDto= customerDto.getAccountsDto();
        if(accountsDto!= null){
            //update account by accountsNumber
            Long accountNumber= accountsDto.getAccountNumber();
            Accounts accounts= accountRepository.findById(accountNumber).orElseThrow(()-> new ResourceNoFoundException("Accounts","AccountsNumber",accountNumber.toString()));

            accountRepository.save(AccountsMapper.mapToAccount(accountsDto,accounts));
            Long customerId= accounts.getCustomerId();
            Customer customer= customerRepository.findById(customerId).orElseThrow(()-> new ResourceNoFoundException("Customer","CustomerId", customerId.toString()));
            customerRepository.save(CustomerMapper.mapToCustomer(customerDto,customer));
            isUpdate= true;

        }

        return isUpdate;
    }

    @Override
    public boolean deleteByMobileNumber(String mobileNumber) {
        Customer customer= customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->
                new ResourceNoFoundException("Customer","MobileNumber",mobileNumber));
        customerRepository.deleteById(customer.getId());
        accountRepository.deleteByCustomerId(customer.getId());
        return true;
    }

    private Accounts createNewAccounts(Customer customer){
        Accounts accounts= new Accounts();
        long randomNumber= 1000000000L + new Random().nextInt(900000000) ;
        accounts.setAccountNumber(randomNumber);
        accounts.setCustomerId(customer.getId());
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        return accounts;
    }
}
