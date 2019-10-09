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
	 * ��ѯ
	 * 
	 * ʹ�÷���
	 * 
	 * 1.��entity����,�½������ݿ����ͬ�ֶεĶ���T,���ɹ��췽��
	 * 
	 * 2.��ʹ��BaseDao����A�м̳�BaseDao
	 * ��:
	 * public class A extends BaseDao<T>{......}
	 * ����TΪ��Ҫ��ѯд��Ķ���T
	 * 
	 * 3.����A
	 * ��:
	 * A a=new A();
	 * 
	 * 4.�����б���ղ�ѯ���ض���,sql����SQL���,�����?,��˳����?��ֵ.
	 * ���:��?
	 * List<T> list = a.selectData(sql,T.class);
	 * ���:SQL�������δֵ֪?
	 * List<T> list = a.selectData(sql,T.class,����1,����2,......,����n);
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
	 * �������ز�ѯ�Ƿ�ɹ�
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
	 * ����ɾ����
	 * 
	 * ʹ�÷���
	 * 
	 * 1.��entity����,�½������ݿ����ͬ�ֶεĶ���T,���ɹ��췽��
	 * 
	 * 2.��ʹ��BaseDao����A�м̳�BaseDao
	 * ��:
	 * public class A extends BaseDao<T>{......}
	 * ����TΪ��Ҫ��ѯд��Ķ���T
	 * 
	 * 3.����A
	 * ��:
	 * A a=new A();
	 * 
	 * 4.�����б���ղ�ѯ���ض���,sql����SQL���,�����?,��˳����?��ֵ.���з���ֵΪ����ɾ�������ݵ�����
	 * ���:��?
	 * int flag = a.executeData(sql,T.class);
	 * ���:SQL�������δֵ֪?
	 * int flag = a.executeData(sql,T.class,����1,����2,......,����n);
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
				//������������ݿ���ͨ������ķ����õ���Ӧ���������е���ֵ
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
	 * ������������ɾ�����Ƿ�ɹ�
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public boolean isValidexecuteData(String sql,Object...args) {
		return executeData(sql,args)!=0;
	}
}
