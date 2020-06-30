package com.example.bank.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.dto.AmountTransferDto;
import com.example.bank.dto.BankExceptionDto;
import com.example.bank.dto.CustomerDetailDto;
import com.example.bank.dto.RegisterCustomerDto;
import com.example.bank.exception.BankValidatonException;
import com.example.bank.service.BankService;

@RestController
@RequestMapping(value = "/api/bank")
@Validated
public class BankController {
	
	BankService service;
	
	public BankController(BankService service) {
		this.service=service;
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<CustomerDetailDto> register(@Valid @RequestBody RegisterCustomerDto register, 
			BindingResult result) {
		if(result.hasErrors()) {
		throw new BankValidatonException("Password not in correct format!");	
		}
		
		CustomerDetailDto userDetailDto = this.service.registerCustomer(register);
		ResponseEntity<CustomerDetailDto> response = new ResponseEntity<CustomerDetailDto>(userDetailDto, HttpStatus.OK);
		return response;
	}
	
	@PostMapping(value = "/transfer")
	public ResponseEntity<HttpStatus> transferAmount(@RequestBody AmountTransferDto transfer) {
		Boolean transferStatus= this.service.transferAmmount(transfer);
		HttpStatus status =HttpStatus.BAD_REQUEST;
		if(transferStatus)
			status =HttpStatus.OK;
			
		ResponseEntity<HttpStatus> response = new ResponseEntity<HttpStatus>(status);
		return response;
	}
	
	
	
	@DeleteMapping(value = "/customers")
	public ResponseEntity<HttpStatus> removeCustomer(@RequestBody CustomerDetailDto custDto) {
		Boolean transferStatus= this.service.removeCustomer(custDto.getCustId());
		HttpStatus status =HttpStatus.BAD_REQUEST;
		if(transferStatus)
			status =HttpStatus.OK;
			
		ResponseEntity<HttpStatus> response = new ResponseEntity<HttpStatus>(status);
		return response;
	}
	
	
	@GetMapping(value = "/customers")
	public ResponseEntity<CustomerDetailDto> getCustomer() {
		
		CustomerDetailDto userDetailDto = this.service.getCustomerDetail("");
		ResponseEntity<CustomerDetailDto> response = new ResponseEntity<CustomerDetailDto>(userDetailDto, HttpStatus.OK);
		return response;
	}
	
	
	
	
	
	@ExceptionHandler(BankValidatonException.class)
	public ResponseEntity<BankExceptionDto> boundaryExceptionHanler(BankValidatonException ex) {
		BankExceptionDto userExceptionDto = 
				new BankExceptionDto(ex.getMessage(), 
									HttpStatus.BAD_REQUEST.value(), 
									System.currentTimeMillis());
		ResponseEntity<BankExceptionDto> response = 
				new ResponseEntity<BankExceptionDto>(userExceptionDto, HttpStatus.BAD_REQUEST);
		return response;
	}
	
	

}
