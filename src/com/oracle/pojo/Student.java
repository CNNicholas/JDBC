package com.oracle.pojo;

import java.io.Serializable;

public class Student implements Serializable{
	private int snum;
	private String sname;
	private int sage;
	private double money;
	public int getSnum() {
		return snum;
	}
	public void setSnum(int snum) {
		this.snum = snum;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getSage() {
		return sage;
	}
	public void setSage(int sage) {
		this.sage = sage;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Student(int snum, String sname, int sage, double money) {
		super();
		this.snum = snum;
		this.sname = sname;
		this.sage = sage;
		this.money = money;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Student [snum=" + snum + ", sname=" + sname + ", sage=" + sage + ", money=" + money + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(money);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + sage;
		result = prime * result + ((sname == null) ? 0 : sname.hashCode());
		result = prime * result + snum;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (Double.doubleToLongBits(money) != Double.doubleToLongBits(other.money))
			return false;
		if (sage != other.sage)
			return false;
		if (sname == null) {
			if (other.sname != null)
				return false;
		} else if (!sname.equals(other.sname))
			return false;
		if (snum != other.snum)
			return false;
		return true;
	}

	
	
}
