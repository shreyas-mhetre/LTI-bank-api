package com.example.bank.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankExceptionDto {

	private String message;
	private Integer errorCode;
	private Long timeStamp;
}