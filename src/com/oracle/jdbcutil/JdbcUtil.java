package com.oracle.jdbcutil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/**
 * 封装jdbcutil静态工具类
 * @author 朱致宇1999
 *
 */
public class JdbcUtil {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	static {
		Properties p = new Properties();
		
		InputStream is = JdbcUtil.class.getResourceAsStream("/db.properties");
		try {
			p.load(is);
			
			driver = p.getProperty("driver");
			url = p.getProperty("url");
			username = p.getProperty("username");
			password = p.getProperty("password");
			//加载驱动
			Class.forName(driver);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取Connection对象
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//封装获取PreparedStatement对象
	
	public static PreparedStatement getpreparedStatement(String sql,Connection conn) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}
	
	//封装获取Statement对象
	public static Statement getStatement(Connection conn) { 
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stmt;
	}
	//封装DML
	public static int executeDML(String sql,Object...obj) {
		Connection conn = getConnection();
		
		PreparedStatement ps = getpreparedStatement(sql, conn);
		try {
			conn.setAutoCommit(false);
			for(int i = 0;i<obj.length;i++) {
				ps.setObject(i+1, obj[i]);
			}
			int i = ps.executeUpdate();
			conn.commit();
			return i;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			finally {
				JdbcUtil.closeAll(null, ps, conn);
			}
		}
		return -1;
	}
	//关闭资源
	public static void closeAll(ResultSet rs,Statement stmt,Connection conn) {
		try {
			rs.close();
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
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
