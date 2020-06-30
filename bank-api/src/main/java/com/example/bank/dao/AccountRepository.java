package com.example.bank.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.bank.document.AccountDetail;

@Repository
public interface AccountRepository extends CrudRepository<AccountDetail, String> {
	
	List<AccountDetail> findByCustId(String custId);
	void deleteByCustId(String custId);

}
