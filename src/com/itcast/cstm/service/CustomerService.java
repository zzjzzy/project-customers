package com.itcast.cstm.service;

import java.util.List;

import com.itcast.cstm.dao.CustomerDao;
import com.itcast.cstm.domain.Customer;

public class CustomerService {

	CustomerDao customerDao = new CustomerDao();
	
	public void add(Customer customer) {
		customerDao.add(customer);
	}
	
	public List<Customer> findAll(){
		return customerDao.findAll();
	}
}
