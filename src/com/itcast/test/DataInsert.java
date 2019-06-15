package com.itcast.test;

import java.util.Date;

import org.junit.Test;

import com.itcast.cstm.dao.CustomerDao;
import com.itcast.cstm.domain.Customer;

import cn.itcast.commons.CommonUtils;

public class DataInsert {
	
	@Test
	public void insert() {
		CustomerDao customerDao = new CustomerDao();
		for (int i = 0; i < 300; i++) {
			Customer c = new Customer();
			c.setCid(CommonUtils.uuid());
			c.setCname("客户"+i);
			c.setBirthday(new Date());
			c.setCellphone("139"+i);
			c.setEmail("cstm"+i+"@163.com");
			c.setGender(i%2==0?"男":"女");
			c.setDescription("我是客户"+i);
			customerDao.add(c);
		}
	}
}
