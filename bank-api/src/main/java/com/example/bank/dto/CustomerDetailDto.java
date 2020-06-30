package com.example.bank.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDetailDto {
	private String custId;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNum;
	private List<AccountDto> accountList;
}
