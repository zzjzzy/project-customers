package com.itcast.cstm.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.itcast.cstm.domain.Customer;
import com.itcast.cstm.domain.PageBean;
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
	
	public String preEdit(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String cid = request.getParameter("cid");
		Customer customer = customerService.findOne(cid);
		request.setAttribute("cstm", customer);
		return "f:/edit.jsp";
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Customer customer = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		customerService.updateOne(customer);
		request.setAttribute("msg", "恭喜，编辑成功！");
		return "f:/msg.jsp";
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		customerService.delete(request.getParameter("cid"));
		request.setAttribute("msg", "恭喜，删除成功！");
		return "f:/msg.jsp";
	}
	
	public String query(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//因为是按条件查询，所以取名为criteria。
		Customer criteria = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		List<Customer> list = customerService.query(criteria);
		request.setAttribute("cstmList", list);
		return "f:/list.jsp";
	}
	
	public String findPageData(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int pc = getPc(request);
		int ps = 10;
		PageBean<Customer> pageBean = customerService.findPageData(ps, pc);
		request.setAttribute("pageBean", pageBean);
//		request.setAttribute("msg", "查询成功");
//		System.out.println(pageBean.getBeanList().size());
//		System.out.println(pageBean.getTr());
		return "f:/list-page.jsp";
	}
	
	public int getPc(HttpServletRequest request) {
		String pcpc = request.getParameter("pc");
		if (pcpc == null || pcpc.trim().isEmpty()) {
			return 1;
		} else {
			return Integer.parseInt(pcpc);
		}
	}
	
	public String query2(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int pc = getPc(request);
		int ps = 10;
		String url = getUrl(request);
//		System.out.println(url);
		Customer criteria = CommonUtils.toBean(request.getParameterMap(), Customer.class);
//		criteria = encoding(criteria);
//		System.out.println(criteria.getGender());
		PageBean<Customer> pageBean = customerService.query2(criteria, ps, pc);
		pageBean.setUrl(url);
		request.setAttribute("pageBean", pageBean);
		return "f:/list-page2.jsp";
	}

	private Customer encoding(Customer criteria) throws UnsupportedEncodingException {
			String cname = criteria.getCname();
			String gender = criteria.getGender();
			String cellphone = criteria.getCellphone();
			String email = criteria.getEmail();
			
			if(cname != null && !cname.trim().isEmpty()) {
				cname = new String(cname.getBytes("ISO-8859-1"), "utf-8");
				criteria.setCname(cname);
			}
			
			if(gender != null && !gender.trim().isEmpty()) {
				gender = new String(gender.getBytes("ISO-8859-1"), "utf-8");
				criteria.setGender(gender);
			}
			
			if(cellphone != null && !cellphone.trim().isEmpty()) {
				cellphone = new String(cellphone.getBytes("ISO-8859-1"), "utf-8");
				criteria.setCellphone(cellphone);
			}
			
			if(email != null && !email.trim().isEmpty()) {
				email = new String(email.getBytes("ISO-8859-1"), "utf-8");
				criteria.setEmail(email);
			}
			return criteria;		
	}

	private String getUrl(HttpServletRequest request) {
		String contextPath = request.getContextPath();
//		System.out.println(contextPath);
		String servletPath = request.getServletPath();
//		System.out.println(servletPath);
		String queryString = request.getQueryString();
//		System.out.println(queryString);
		if (queryString.contains("&pc=")) {
			int index = queryString.lastIndexOf("&pc=");
			queryString = queryString.substring(0, index);
		}
		return contextPath + servletPath + "?" + queryString;
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
