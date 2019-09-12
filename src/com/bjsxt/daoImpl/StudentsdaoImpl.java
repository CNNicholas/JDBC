package com.bjsxt.daoImpl;
/**
 * 使用Statement
 * 有SQL注入风险
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.oracle.pojo.Student;

public class StudentsdaoImpl {

	private ArrayList<Student> stulist = new ArrayList<>();
	
	private Connection conn = null;
	private Statement stmt = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String username = "student";
	private String password = "zhuzhiyu";
	
	public Connection getConn() {
		return conn;
	}

	public StudentsdaoImpl() {
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//数据查询
	public ArrayList<Student> Select(){
			
			ResultSet rq;
			try {
				rq = stmt.executeQuery("select * from student order by snum");
				while(rq.next()) {
					Student stu = new Student();
					
					stu.setSnum(rq.getInt("snum"));
					stu.setSname(rq.getString("sname"));
					stu.setSage(rq.getInt("sage"));
					stu.setMoney(rq.getDouble("money"));
					
					stulist.add(stu);
					try {
						rq.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return stulist;
		
	}
	//数据增加
	public int StudentsInsert(Student stu){
		
			int i;
			try {
				i = stmt.executeUpdate("insert into student values("+stu.getSnum()+",'"+stu.getSname()+"',"+stu.getSage()+",'"+stu.getMoney()+"')");
				return i;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return -1;
			}
	}
	//数据更新
	public int StudentsUpdate(int snum,String word,String newword){  
		
		int i;
		try {
			i = stmt.executeUpdate("update student set "+word+"='"+newword+"' where snum= "+snum);
			return i;
		} catch (SQLException e) {	//("update student set sname='"+newName+"' where snum="+snum)
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return -1;
		}
			
	
	
	}
	//数据删除
	public int StudentsDelete(int snum){  
		
		int i;
		try {
			
			 i = stmt.executeUpdate("delete from student where snum= "+snum);
			 return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return -1;
		}
			
	
	
	}
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

