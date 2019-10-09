package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import util.C3P0Utils;

public class BaseDao<T> {
	/**
	 * @param sql
	 * @param clazz
	 * @param args
	 * @return
	 * 
	 * 查询
	 * 
	 * 使用方法
	 * 
	 * 1.在entity包中,新建与数据库表相同字段的对象T,生成构造方法
	 * 
	 * 2.在使用BaseDao的类A中继承BaseDao
	 * 如:
	 * public class A extends BaseDao<T>{......}
	 * 其中T为需要查询写入的对象T
	 * 
	 * 3.构造A
	 * 如:
	 * A a=new A();
	 * 
	 * 4.创建列表接收查询返回对象,sql代表SQL语句,如果有?,则按顺序传入?的值.
	 * 如①:无?
	 * List<T> list = a.selectData(sql,T.class);
	 * 如②:SQL语句中有未知值?
	 * List<T> list = a.selectData(sql,T.class,参数1,参数2,......,参数n);
	 */
	public List<T> selectData(String sql,Class<T> clazz,Object...args){
		List<T> list = new ArrayList<T>();
		Connection conn=C3P0Utils.getConnection();
		PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
			ps = conn.prepareStatement(sql);
			if (null!=args&&args.length>0) {
				for(int i=0;i<args.length;i++) {
					ps.setObject(i+1, args[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				T t = clazz.newInstance();
				for(int i=1;i<=metaData.getColumnCount();i++){
					String columnName = metaData.getColumnName(i);
					Object value = rs.getObject(i);
					//System.out.println(columnName+":"+value);
					BeanUtils.setProperty(t, columnName, value);    
				}
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}finally{
			C3P0Utils.closeConnection(rs, ps, conn);
		}	
		return list;
	}
	/**
	 * 
	 * 仅仅返回查询是否成功
	 * 
	 * @param sql
	 * @param clazz
	 * @param args
	 * @return
	 */
	public boolean isValidselectData(String sql,Class<T> clazz,Object...args) {
		return !selectData(sql,clazz,args).isEmpty();
	}
	/**
	 * 增、删、改
	 * 
	 * 使用方法
	 * 
	 * 1.在entity包中,新建与数据库表相同字段的对象T,生成构造方法
	 * 
	 * 2.在使用BaseDao的类A中继承BaseDao
	 * 如:
	 * public class A extends BaseDao<T>{......}
	 * 其中T为需要查询写入的对象T
	 * 
	 * 3.构造A
	 * 如:
	 * A a=new A();
	 * 
	 * 4.创建列表接收查询返回对象,sql代表SQL语句,如果有?,则按顺序传入?的值.其中返回值为增、删、改数据的条数
	 * 如①:无?
	 * int flag = a.executeData(sql,T.class);
	 * 如②:SQL语句中有未知值?
	 * int flag = a.executeData(sql,T.class,参数1,参数2,......,参数n);
	 */
	public int executeData(String sql,Object...args){
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = C3P0Utils.getConnection();
			try{			
				ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				if (null!=args && args.length>0) {
					for (int i=0;i<args.length;i++) {
						ps.setObject(i+1,args[i]);
					}
				}
				//如果是增加数据可以通过下面的方法得到对应的自增长列的数值
				ps.getGeneratedKeys();
				result = ps.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}finally{
				C3P0Utils.closeConnection(rs, ps, conn);
			}	
		 return result;
	}
	/**
	 * 
	 * 仅仅返回增、删、改是否成功
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public boolean isValidexecuteData(String sql,Object...args) {
		return executeData(sql,args)!=0;
	}
}
