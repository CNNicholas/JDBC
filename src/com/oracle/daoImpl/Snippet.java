package com.oracle.daoImpl;

import com.oracle.pojo.Student;

public class Snippet {
	public static void main(String[] args) {
			StudentsdaoImpl3 ss = new StudentsdaoImpl3();

			Student stu = new Student(222,"zuiuiui",10,250.0);
			//ss.StudentsInsert(stu);
			//ss.StudentsUpdate(1,"zhij");
			ss.StudentsDelete(222);
			System.out.println(ss.Select());
			
		}
}

