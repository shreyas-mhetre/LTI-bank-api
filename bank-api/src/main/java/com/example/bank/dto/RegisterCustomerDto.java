package com.example.bank.dto;

import java.util.List;

import lombok.Data;

@Data
public class RegisterCustomerDto {
	private String id;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNum;
	private String ssn;
	private List<AccountDto> accountList;
}
