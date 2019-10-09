package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatebaseUtil {
	/**
	 * ��ȡ�����ļ�,Ϊ�������ݿ���׼������.
	 */
	private static String url;
	private static String user;
	private static String password;
	static {
		Properties properties = new Properties();
		try {
			properties.load(DatebaseUtil.class.getResourceAsStream("���ݿ�����.properties"));
			Class.forName(properties.getProperty("driver"));
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		} catch (IOException | ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

	/**
	 * �������ݿ�
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			//System.out.println("�������ݿ�ɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�������ݿ�ʧ��");
		}
		return conn;
	}

	/**
	 * �ر����ݿ�
	 */
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				//System.out.println("�ر����ݿ�ɹ�");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر����ݿ�����ʧ��");
			}
		}
	}
}
