package com.jdbc.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Oracle数据库事务提交
 * 数据库的增删改要使用事务处理，查询不用
 * JDBC为自动提交
 * 设置jdbc事务为手动提交
 * 只有增删改才涉及事务
 *
 * @author 朱致宇1999
 */
public class JDBCdome02 {
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
			conn.setAutoCommit(false);  //设置成手动提交
		//获取SQL命令对象(发送命令给数据库)
			 stmt = conn.createStatement();
		//创建SQL命令
			String sql = "insert into dept values (60,'潍坊'，'wowowo')";
			try {
				//发送SQL命令  返回-1表示失败，非-1，表示修改的数据量
				int i = stmt.executeUpdate(sql);   //如果是银行转账，失败一条立即执行catch，进行数据回滚
				conn.commit();  //数据提交
				System.out.println(i);   //返回结果
			} catch (Exception e) {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			
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
