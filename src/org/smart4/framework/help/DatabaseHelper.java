package org.smart4.framework.help;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @ClassName: DatabaseHelper 
* @Description: 数据库工具类
* @author acer
* @date 2017年6月28日 上午10:13:32 
*  
*/
public final class DatabaseHelper {
	
	private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
	
	private static final BasicDataSource DATA_SOURCE = new BasicDataSource();
	
	
	/** 
	* @Title: getConection 
	* @Description: 获取数据库连接
	* @param @return    
	* @return Connection   
	* @throws 
	* @author
	*/
	public static Connection getConection(){
		Connection conn = CONNECTION_HOLDER.get();
		if(conn != null){
			try{
				conn = DATA_SOURCE.getConnection();
			}catch(SQLException e){
				LOGGER.error("get connection failure",e);
				throw new RuntimeException(e);
			}finally{
				CONNECTION_HOLDER.set(conn);
			}
		}
		
		return conn;
	}
	
	/** 
	* @Title: closeConnection 
	* @Description:关闭数据库连接
	* @param @param conn    
	* @return void   
	* @throws 
	* @author
	*/
	public static void closeConnection(){
		Connection conn = CONNECTION_HOLDER.get();
		if(conn != null){
			try{
				conn.close();
			}catch(SQLException e){
				LOGGER.error("get connection failure",e);
				throw new RuntimeException(e);
			}finally{
				CONNECTION_HOLDER.remove();
			}
		}
		
	}
	

	
	/** 
	* @Title: beginTransaction 
	* @Description: 开启事务
	* @param     
	* @return void   
	* @throws 
	* @author
	*/
	public static void beginTransaction(){
		Connection conn = getConection();
		if(conn != null){
			try{
				conn.setAutoCommit(false);
			}catch(SQLException e){
				LOGGER.error("begin transaction failure",e);
				throw new RuntimeException(e);
			}finally{
				CONNECTION_HOLDER.set(conn);
			}
		}
	}
	/** 
	* @Title: commitTransaction 
	* @Description: 提交事务
	* @param     
	* @return void   
	* @throws 
	* @author
	*/
	public static void commitTransaction(){
		Connection conn = getConection();
		if(conn != null){
			try{
				conn.commit();
				conn.close();
			}catch(SQLException e){
				LOGGER.error("commit transaction failure",e);
				throw new RuntimeException(e);
			}finally{
				CONNECTION_HOLDER.remove();
			}
		}
	}
	
	public static void rollbackTransaction(){
		Connection conn = getConection();
		if(conn != null){
			try{
				conn.rollback();
				conn.close();
			}catch(SQLException e){
				LOGGER.error("rollback transaction failure",e);
				throw new RuntimeException(e);
			}finally{
				CONNECTION_HOLDER.remove();
			}
		}
	}
}
