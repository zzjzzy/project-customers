package com.itcast.cstm.service;

import java.util.List;

import com.itcast.cstm.dao.CustomerDao;
import com.itcast.cstm.domain.Customer;
import com.itcast.cstm.domain.PageBean;

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
	
	public void delete(String cid) {
		customerDao.delete(cid);
	}
	
	public List<Customer> findByParam(String cname){
		return customerDao.findByParam(cname);
	}

	public List<Customer> query(Customer criteria) {
		return customerDao.query(criteria);
	}
	
	public PageBean<Customer> findPageData(int ps, int pc){
		return customerDao.findPageData(ps, pc);
	}
	
	public PageBean<Customer> query2(Customer criteria, int ps, int pc){
		return customerDao.query2(criteria, ps, pc);
	}
}
