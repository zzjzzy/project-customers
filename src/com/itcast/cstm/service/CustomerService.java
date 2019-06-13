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
	
	public Customer findOne(String cid) {
		return customerDao.findOne(cid);
	}
	
	public void updateOne(Customer customer) {
		customerDao.updateOne(customer);
	}
}
