package com.example.bank.document;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetail {
	@Id
	private String accountNum;
	private String type;
	private long amount;
	private String custId;
}
