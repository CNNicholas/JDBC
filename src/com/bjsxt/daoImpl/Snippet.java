package com.bjsxt.daoImpl;

import com.oracle.pojo.Student;

public class Snippet {
	public static void main(String[] args) {
			StudentsdaoImpl2 ss = new StudentsdaoImpl2();
			//System.out.println(ss.StudentsDelete(2));
			//ss.StudentsInsert(new Student(505,"zuiuiui",10,250));
			System.out.println(ss.Select());
			ss.close();
		}
}

