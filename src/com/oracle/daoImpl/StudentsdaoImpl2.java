package com.oracle.daoImpl;
/**
 * 使用prepareStatement
 * 有效防止SQL注入
 * 提高运行效率（采用预编译）
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oracle.pojo.Student;

public class StudentsdaoImpl2 {

	private ArrayList<Student> stulist = new ArrayList<>();
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String username = "student";
	private String password = "zhuzhiyu";
	
	public Connection getConn() {
		return conn;
	}

	public StudentsdaoImpl2() {
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
			//stmt = conn.createStatement();
			
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
				//rq = stmt.executeQuery();
				String sql = "select * from student order by snum";
				ps = conn.prepareStatement(sql);
				rq = ps.executeQuery();
				while(rq.next()) {
					Student stu = new Student();
					
					stu.setSnum(rq.getInt("snum"));
					stu.setSname(rq.getString("sname"));
					stu.setSage(rq.getInt("sage"));
					stu.setMoney(rq.getDouble("money"));
					
					stulist.add(stu);
					//rq.close();   !!!!!!!
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
				String sql = "insert into student values(?,'?',?,?)";
				//i = stmt.executeUpdate("insert into student values("+stu.getSnum()+",'"+stu.getSname()+"',"+stu.getSage()+",'"+stu.getMoney()+"')");
				ps = prepareStatement(sql);
				ps.setInt(1, stu.getSnum());
				ps.setString(2, stu.getSname());
				ps.setInt(3, stu.getSage());
				ps.setDouble(4, stu.getMoney());
				i = ps.executeUpdate();
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
	private PreparedStatement prepareStatement(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	//数据更新
	public int StudentsUpdate(int snum,String word,String newword){  
		
		int i;
		try {
			String sql = "update student set ?=? where snum= ?";
			//i = stmt.executeUpdate("update student set "+word+"='"+newword+"' where snum= "+snum);
			ps = conn.prepareStatement(sql);
			ps.setString(1, word);
			ps.setString(2, newword);
			ps.setInt(3, snum);
			i = ps.executeUpdate();
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
			 String sql = "delete from student where snum= ?";
			 //i = stmt.executeUpdate("delete from student where snum= "+snum);
			 ps = conn.prepareStatement(sql);
			 ps.setInt(1, snum);
			 i = ps.executeUpdate();
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
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

