package com.PTD.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "AccountsNumber không thể là null hoặc empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "AccountNumber phải là một số gồm 10 chữ số")
    private Long accountNumber;

    @NotEmpty(message = "AccountType không thể là null hoặc empty")
    private String accountType;

    @NotEmpty(message = "BranchAddress không thể là null hoặc empty")
    private String branchAddress;
}
