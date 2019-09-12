package com.jdbc.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Oracle���ݿ������ύ
 * ���ݿ����ɾ��Ҫʹ����������ѯ����
 * JDBCΪ�Զ��ύ
 * ����jdbc����Ϊ�ֶ��ύ
 * ֻ����ɾ�Ĳ��漰����
 *
 * @author ������1999
 */
public class JDBCdome02 {
	public static void main(String[] args){
		
		String driver = "oracle.jdbc.deiver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String username = "stduent";
		String password = "zhuzhiyu";
		
		Connection conn = null;
		Statement stmt = null;
		//����������
		try {
			Class.forName(driver);
		//��ȡ���ݿ����Ӷ���
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);  //���ó��ֶ��ύ
		//��ȡSQL�������(������������ݿ�)
			 stmt = conn.createStatement();
		//����SQL����
			String sql = "insert into dept values (60,'Ϋ��'��'wowowo')";
			try {
				//����SQL����  ����-1��ʾʧ�ܣ���-1����ʾ�޸ĵ�������
				int i = stmt.executeUpdate(sql);   //���������ת�ˣ�ʧ��һ������ִ��catch���������ݻع�
				conn.commit();  //�����ύ
				System.out.println(i);   //���ؽ��
			} catch (Exception e) {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			
		}
		//�ر���Դ
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
