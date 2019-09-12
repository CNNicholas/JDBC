package com.jdbc.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 仅用于增删改
 * @author 朱致宇1999
 *
 */
public class JDBCdome01 {
	public static void main(String[] args){
		
		String driver = "oracle.jdbc.deiver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String username = "stduent";
		String password = "zhuzhiyu";
		
		Connection conn = null;
		Statement stmt = null;
		//加载驱动类
		try {
			Class.forName(driver);
		//获取数据库连接对象
			conn = DriverManager.getConnection(url, username, password);
		//获取SQL命令对象(发送命令给数据库)
			 stmt = conn.createStatement();
		//创建SQL命令
			String sql = "insert into dept values (60,'潍坊'，'wowowo')";
		//发送SQL命令  返回-1表示失败，非-1，表示修改的数据量
			System.out.println(stmt.executeUpdate(sql));

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//关闭资源
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
