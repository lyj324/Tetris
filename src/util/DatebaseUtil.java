package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatebaseUtil {
	/**
	 * 读取配置文件,为连接数据库做准备工作.
	 */
	private static String url;
	private static String user;
	private static String password;
	static {
		Properties properties = new Properties();
		try {
			properties.load(DatebaseUtil.class.getResourceAsStream("数据库配置.properties"));
			Class.forName(properties.getProperty("driver"));
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		} catch (IOException | ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 连接数据库
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			//System.out.println("连接数据库成功");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("连接数据库失败");
		}
		return conn;
	}

	/**
	 * 关闭数据库
	 */
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				//System.out.println("关闭数据库成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭数据库连接失败");
			}
		}
	}
}
