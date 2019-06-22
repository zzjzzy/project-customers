package com.itcast.cstm.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itcast.cstm.domain.Customer;
import com.itcast.cstm.domain.PageBean;
import com.mysql.fabric.xmlrpc.base.Array;

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

	public List<Customer> query(Customer criteria) {
		try {
			StringBuilder sql = new StringBuilder("select * from customer where 1=1 ");
			List<Object> params = new ArrayList<Object>();
			String cname = criteria.getCname();
			if (cname != null && !cname.trim().isEmpty()) {
				sql.append("and cname like ? ");
				params.add("%" + cname + "%");
			}
			
			String gender = criteria.getGender();
			if (gender != null && !gender.trim().isEmpty()) {
				sql.append("and gender = ? ");
				params.add(gender);
			}
			
			String email = criteria.getEmail();
			if (email != null && !email.trim().isEmpty()) {
				sql.append("and email like ? ");
				params.add("%" + email + "%");
			}
			
			String cellphone = criteria.getCellphone();
			if (cellphone != null && !cellphone.trim().isEmpty()) {
				sql.append("and cellphone like ? ");
				params.add("%" + cellphone + "%");
			}
			
			return qr.query(sql.toString(), 
					new BeanListHandler<Customer>(Customer.class), 
					params.toArray());
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//查找分页数据
	public PageBean<Customer> findPageData(int ps, int pc){
		try {
			PageBean<Customer> pageBean = new PageBean<Customer>();
			pageBean.setPc(pc);
			pageBean.setPs(ps);

			String sql_tr = "select count(*) from customer";
			Number number = (Number) qr.query(sql_tr, new ScalarHandler());
			int tr = number.intValue();
			pageBean.setTr(tr);
			
			String sql_pageData = "select * from customer limit ?,?";
			List<Customer> beanList = qr.query(sql_pageData, new BeanListHandler<Customer>(Customer.class), (pc-1)*ps, ps);
			pageBean.setBeanList(beanList);
			
			return pageBean;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public PageBean<Customer> query2(Customer criteria, int ps, int pc) {
		try {
			StringBuilder cntSql = new StringBuilder("select count(*) from customer");//count计数sql
			StringBuilder whereSql = new StringBuilder(" where 1=1 ");
			List<Object> params = new ArrayList<Object>();
			
			String cname = criteria.getCname();
			if (cname != null && !cname.trim().isEmpty()) {
				whereSql.append("and cname like ? ");
				params.add("%"+cname+"%");
			}
			
			String gender = criteria.getGender();
			if (gender != null && !gender.trim().isEmpty()) {
				whereSql.append("and gender = ? ");
				params.add(gender);
			}
			
			String email = criteria.getEmail();
			if (email != null && !email.trim().isEmpty()) {
				whereSql.append("and email like ? ");
				params.add("%" + email + "%");
			}
			
			String cellphone = criteria.getCellphone();
			if (cellphone != null && !cellphone.trim().isEmpty()) {
				whereSql.append("and cellphone like ? ");
				params.add("%" + cellphone + "%");
			}
			
			
			Number num = (Number)qr.query(cntSql.append(whereSql).toString(), new ScalarHandler(), params.toArray());
			int tr = num.intValue();
			
			PageBean<Customer> pb = new PageBean<Customer>();
			pb.setPc(pc);
			pb.setPs(ps);
			pb.setTr(tr);
			
			StringBuilder limitSql = new StringBuilder("limit ?,?");
			params.add(ps*(pc-1));
			params.add(ps);
			
			StringBuilder sql = new StringBuilder("select * from customer");
			
			List<Customer> list = qr.query(sql.append(whereSql).append(limitSql).toString(), 
					new BeanListHandler<Customer>(Customer.class), params.toArray());
			pb.setBeanList(list);
			
			return pb;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
