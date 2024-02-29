package com.PTD.accounts.controller;

import com.PTD.accounts.dto.CustomerDetailDto;
import com.PTD.accounts.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CustomerController {

    private ICustomerService customerService;
    @GetMapping("/fetchCustomerDetail")
    public ResponseEntity<CustomerDetailDto> fetchCustomerDetail(String mobileNumber){
        CustomerDetailDto customerDetailDto= customerService.fetchCustomerDetail(mobileNumber);
        return ResponseEntity.status(HttpStatus.SC_OK).body(customerDetailDto);
    }
}
