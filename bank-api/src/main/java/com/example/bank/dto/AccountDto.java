package com.example.bank.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {
	private String accountNum;
	private String type;
	private long amount;
	private String custId;
}
