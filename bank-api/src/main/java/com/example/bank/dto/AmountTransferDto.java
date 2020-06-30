package com.example.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AmountTransferDto {
	private String fromAccount;
	private String toAccount;
	private long transferAmount;
}
