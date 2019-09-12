package com.oracle.daoImpl;

import com.oracle.pojo.Student;

public class Snippet {
	public static void main(String[] args) {
			StudentsdaoImpl2 ss = new StudentsdaoImpl2();

			//Student stu = new Student(222,"zuiuiui",10,250.0);
			//ss.StudentsInsert(stu);
			ss.StudentsUpdate(1, "sage","1231");
			System.out.println(ss.Select());
			ss.close();
		}
}

