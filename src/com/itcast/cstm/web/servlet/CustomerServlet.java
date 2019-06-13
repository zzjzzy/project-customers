package com.itcast.cstm.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.itcast.cstm.domain.Customer;
import com.itcast.cstm.service.CustomerService;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	CustomerService customerService = new CustomerService();

	public String add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Customer customer = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		customer.setCid(CommonUtils.uuid());
		customerService.add(customer);
		request.setAttribute("msg", "恭喜，添加客户成功！");
		return "f:/msg.jsp";
	}
	
	public String findAll(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<Customer> list = customerService.findAll();
		request.setAttribute("cstmList", list);
		return "f:/list.jsp";
	}
	
//	@Test
//	public void add() {
//		Customer c = new Customer();
//		c.setCid(CommonUtils.uuid());
//		c.setCname("aaa");
//		c.setGender("male");
//		c.setBirthday(new Date());
//		c.setCellphone("123");
//		c.setEmail("aaa");
//		customerService.add(c);
//		
//	}
}