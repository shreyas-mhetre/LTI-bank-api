package com.example.bank.service;

import com.example.bank.dto.AmountTransferDto;
import com.example.bank.dto.CustomerDetailDto;
import com.example.bank.dto.RegisterCustomerDto;

public interface BankService {
	public CustomerDetailDto registerCustomer(RegisterCustomerDto registerDto);
	public CustomerDetailDto addAccount(RegisterCustomerDto registerDto);
	public boolean transferAmmount(AmountTransferDto transferDto);
	public CustomerDetailDto getCustomerDetail(String custId);
	public boolean removeCustomer(String custId);

}
