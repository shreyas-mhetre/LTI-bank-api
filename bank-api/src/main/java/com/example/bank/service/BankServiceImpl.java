package com.example.bank.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.dao.AccountRepository;
import com.example.bank.dao.CustomerRepository;
import com.example.bank.document.AccountDetail;
import com.example.bank.document.CustomerDetail;
import com.example.bank.dto.AccountDto;
import com.example.bank.dto.AmountTransferDto;
import com.example.bank.dto.CustomerDetailDto;
import com.example.bank.dto.RegisterCustomerDto;
import com.example.bank.exception.BankValidatonException;

@Service
public class BankServiceImpl implements BankService {
	@Autowired
	CustomerRepository custRepository;
	@Autowired
	AccountRepository acntRepository;
	
	

	@Override
	public CustomerDetailDto registerCustomer(RegisterCustomerDto registerDto) {
		CustomerDetail customer = new CustomerDetail(null, registerDto.getFirstName(), 
				registerDto.getLastName(), 
				registerDto.getAddress(), 
				registerDto.getPhoneNum(),registerDto.getSsn());
		customer= custRepository.save(customer);
		
		List<AccountDetail> accList = new ArrayList<>();
		for(AccountDto acc: registerDto.getAccountList()) {
			accList.add(new AccountDetail(null, acc.getType(), acc.getAmount(), customer.getId()));
		}
		accList= (List<AccountDetail>) acntRepository.saveAll(accList);

		
		List<AccountDto> accDtoList = new ArrayList<>();
		accList.forEach(acc->{
			accDtoList.add(new AccountDto(acc.getAccountNum(), acc.getType(), acc.getAmount(), acc.getCustId()));
		});
		
		
		CustomerDetailDto userDto= new CustomerDetailDto(customer.getId(), customer.getFirstName(), 
				customer.getLastName(), customer.getAddress(), customer.getPhoneNum(), accDtoList);
		return userDto;
	}



	@Override
	public CustomerDetailDto addAccount(RegisterCustomerDto registerDto) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean transferAmmount(AmountTransferDto transferDto) {
		// TODO Auto-generated method stub
		List<String> accList=new ArrayList<String>();
		accList.add(transferDto.getFromAccount());
		accList.add(transferDto.getToAccount());
		AccountDetail debitAcc = new AccountDetail();
		AccountDetail creditAcc = new AccountDetail();
		for(AccountDetail accDetail : acntRepository.findAllById(accList)) {
			if(transferDto.getFromAccount().equals(accDetail.getAccountNum())) {
				debitAcc=accDetail;
			}
			if(transferDto.getToAccount().equals(accDetail.getAccountNum())) {
				creditAcc=accDetail;
			}
		}
		
		if(debitAcc.getAmount()-transferDto.getTransferAmount()<1000) {
			//throw new BankValidatonException("Avaiable balance in account insufficient for this transaction");
			return false;	
		}
		else {
			debitAcc.setAmount(debitAcc.getAmount()-transferDto.getTransferAmount());
			creditAcc.setAmount(creditAcc.getAmount()+transferDto.getTransferAmount());
		}
		List<AccountDetail> updatedAccList = new ArrayList<AccountDetail>();
		updatedAccList.add(creditAcc);
		updatedAccList.add(debitAcc);
		acntRepository.saveAll(updatedAccList);
		return true;
	}



	@Override
	public CustomerDetailDto getCustomerDetail(String custId) {
		List<CustomerDetail> custList =  (List<CustomerDetail>) custRepository.findAll();
		 CustomerDetail cust=custList.get(0);
		
		List<AccountDto> acctList = new ArrayList<AccountDto>();
		for(AccountDetail accDetail : acntRepository.findByCustId(cust.getId())) {
			acctList.add(new AccountDto(accDetail.getAccountNum(), accDetail.getType(), accDetail.getAmount(), accDetail.getCustId()));
		}

		CustomerDetailDto custDto = new CustomerDetailDto(cust.getId(), cust.getFirstName(), 
				cust.getLastName(), cust.getAddress(), cust.getPhoneNum(), acctList);
		return custDto;
	}



	@Override
	public boolean removeCustomer(String custId) {
		acntRepository.deleteByCustId(custId);
		custRepository.deleteById(custId);
		//To change this logic not to hard-delete the records. Records will be inactivated by changing the status.
		return true;
	}


}
