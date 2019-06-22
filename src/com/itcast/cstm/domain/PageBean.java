package com.itcast.cstm.domain;

import java.util.List;

public class PageBean<T> {

	private int pc; //page code当前页码
	private int tr; //total record总记录数
	private int ps; //page size每页记录数
//	private int tp; // total page总页数，通过计算得到，
	private List<T> beanList; //每条记录对应的Bean
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	//total page总页数
	public int getTp() {
		int tp = tr / ps;
		return tr % ps == 0 ? tp : tp + 1;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
}
