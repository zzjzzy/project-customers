package com.itcast.cstm.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itcast.cstm.domain.Customer;

import cn.itcast.jdbc.TxQueryRunner;

public class CustomerDao {

	QueryRunner qr = new TxQueryRunner();
	public void add(Customer customer) {
		String sql = "insert into customer values(?,?,?,?,?,?,?)";
		Object[] params = {customer.getCid(), customer.getCname(), customer.getGender(),
				customer.getBirthday(), customer.getCellphone(), 
				customer.getEmail(), customer.getDescription()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Customer> findAll() {
		try {
			String sql = "select * from customer";
			return qr.query(sql, new BeanListHandler<Customer>(Customer.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Customer findOne(String cid) {
		try {
			String sql = "select * from customer where cid = ?";
			return qr.query(sql, new BeanHandler<Customer>(Customer.class),cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updateOne(Customer customer) {
		String sql = "update customer set cname = ?, gender = ?,"
				+ "birthday = ?, cellphone = ?, email = ?, description = ? where cid = ?";
		Object[] params = {customer.getCname(), customer.getGender(),
				customer.getBirthday(), customer.getCellphone(), 
				customer.getEmail(), customer.getDescription(), customer.getCid()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String cid) {
		String sql = "delete from customer where cid = ?";
		try {
			qr.update(sql, cid);
		} catch (SQLException e) {
			new RuntimeException(e);
		}
	}
	
	public List<Customer> findByParam(String cname){
		String sql = "select * from customer where cname like ?";
		try {
			return qr.query(sql, new BeanListHandler<Customer>(Customer.class), "%"+cname+"%");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
