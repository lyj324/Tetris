package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	/**
	 * ����C3P0����(������������)
	 */
	private static DataSource dataSource = null;
	static{
			//dataSource��Դֻ�ܳ�ʼ��һ��
		dataSource= new ComboPooledDataSource("mysql");
		}
	/**
	 * ��ȡ����
	 */
	public static Connection getConnection() {
		Connection connection=null;
		try {
			connection=dataSource.getConnection();
			//System.out.println("���ݿ����ӳɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("���ݿ�����ʧ��");
		}
		return connection;
	}
	/**
	 * �ر����ݿ�����
	 */
	public static void closeConnection(ResultSet rs,PreparedStatement ps,Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
				//System.out.println("�ر����ݿ�ɹ�");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر����ݿ�ʧ��");
			}
		}
	}
}
