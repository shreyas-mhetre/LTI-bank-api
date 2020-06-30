package com.example.bank.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.bank.document.CustomerDetail;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerDetail, String> {

}
