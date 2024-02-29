package com.PTD.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDetailDto {
    @NotEmpty(message = "Name không thể là Null hoặc Empty")
    @Size(min = 5, max = 30, message = "Độ dài của tên tối thiểu là 5 và tối đa là 30 kí tự")
    private String name;

    @NotEmpty(message = "Email không thể là Null hoặc Empty")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotEmpty(message = "MobileNumber không thể là Null hoặc Empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "MobileNumber  phải là một số gồm 10 chữ số")
    private String mobileNumber;
    private AccountsDto accountsDto;

    private LoansDto loansDto;

    private CardsDto cardsDto;

}
