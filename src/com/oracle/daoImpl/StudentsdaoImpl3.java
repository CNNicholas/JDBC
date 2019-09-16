package com.oracle.daoImpl;
/**
 * ʹ��JDBCUtil��������з�װ
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.oracle.jdbcutil.JdbcUtil;
import com.oracle.pojo.Student;

public class StudentsdaoImpl3 {

	private ArrayList<Student> stulist = new ArrayList<>();

	public StudentsdaoImpl3() {
		
	}
	//���ݲ�ѯ
	public ArrayList<Student> Select(){
			
			String sql = "select * from student order by snum";
			Connection conn = JdbcUtil.getConnection();
			PreparedStatement ps = JdbcUtil.getpreparedStatement(sql, conn);
 
			try {
				ResultSet rq = ps.executeQuery();
				while(rq.next()) {
					Student stu = new Student();
					stu.setSnum(rq.getInt("snum"));
					stu.setSname(rq.getString("sname"));
					stu.setSage(rq.getInt("sage"));
					stu.setMoney(rq.getDouble("money"));
					stulist.add(stu); 
				}
				JdbcUtil.closeAll(rq, ps, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return stulist;
		
	}
	//��������
	public int StudentsInsert(Student stu){
		
		String sql = "insert into student values(?,?,?,?)";
		int i = JdbcUtil.executeDML(sql, stu.getSnum(),stu.getSname(),stu.getSage(),stu.getMoney());
		return i;
	}


	//���ݸ���
	public int StudentsUpdate(int snum,String newword){    //����sname	�����ã��ı��ֶ�
		
		String sql = "update student set sname =? where snum =?";
		int i = JdbcUtil.executeDML(sql,newword,snum);
		return i;
	
	}
	//����ɾ��
	public int StudentsDelete(int snum){  
		String sql = "delete from student where snum =?";
		int i = JdbcUtil.executeDML(sql, snum);
		return i;
	
	}

	
}

