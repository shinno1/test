package com.spring.biz.vo;

public class SalesVO {
	private String buyMonth;
	private int salesPerMonth;
	private int salesPerYear;
	private String buyDay;
	private int salesPerDay;
	
	public String getBuyDay() {
		return buyDay;
	}
	public void setBuyDay(String buyDay) {
		this.buyDay = buyDay;
	}
	public int getSalesPerDay() {
		return salesPerDay;
	}
	public void setSalesPerDay(int salesPerDay) {
		this.salesPerDay = salesPerDay;
	}
	public String getBuyMonth() {
		return buyMonth;
	}
	public void setBuyMonth(String buyMonth) {
		this.buyMonth = buyMonth;
	}
	public int getSalesPerMonth() {
		return salesPerMonth;
	}
	public void setSalesPerMonth(int salesPerMonth) {
		this.salesPerMonth = salesPerMonth;
	}
	public int getSalesPerYear() {
		return salesPerYear;
	}
	public void setSalesPerYear(int salesPerYear) {
		this.salesPerYear = salesPerYear;
	}
	@Override
	public String toString() {
		return "SalesVO [buyMonth=" + buyMonth + ", salesPerMonth=" + salesPerMonth + "]";
	}
	
	
}
