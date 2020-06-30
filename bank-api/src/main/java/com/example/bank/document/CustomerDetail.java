package com.example.bank.document;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDetail {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNum;
	private String ssn;
}
