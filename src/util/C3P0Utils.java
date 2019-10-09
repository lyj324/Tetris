package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	/**
	 * 加载C3P0配置(加载命名配置)
	 */
	private static DataSource dataSource = null;
	static{
			//dataSource资源只能初始化一次
		dataSource= new ComboPooledDataSource("mysql");
		}
	/**
	 * 获取连接
	 */
	public static Connection getConnection() {
		Connection connection=null;
		try {
			connection=dataSource.getConnection();
			//System.out.println("数据库连接成功");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
		return connection;
	}
	/**
	 * 关闭数据库连接
	 */
	public static void closeConnection(ResultSet rs,PreparedStatement ps,Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
				//System.out.println("关闭数据库成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭数据库失败");
			}
		}
	}
}
