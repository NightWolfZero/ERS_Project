package com.revature.ers.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.revature.ers.dao.ReimbursementDao;
import com.revature.ers.dao.UserDao;
import com.revature.ers.dao.impl.ReimbursementDaoImpl;
import com.revature.ers.dao.impl.UserDaoImpl;

public class ConnectionUtils {
private static UserDaoImpl userDaoImpl;
private static ReimbursementDaoImpl reimbursementDaoImpl;
private Connection connection;
private static ConnectionUtils connectionUtils;
    public Connection getConnection(){
        return connection;
    }

	public static synchronized UserDao getUserDao() {

		if (userDaoImpl == null) {
			userDaoImpl = new UserDaoImpl();
		}
		return userDaoImpl;
	}
	public static synchronized ReimbursementDao getReimbursementDao() {

		if (reimbursementDaoImpl == null) {
			reimbursementDaoImpl = new ReimbursementDaoImpl();
		}
		return reimbursementDaoImpl;
	}
    
    private ConnectionUtils() throws SQLException {
        String url=System.getenv("DB_URL");
        String dbName=System.getenv("DB_NAME");
        String user=System.getenv("DB_USER");
        String pwd=System.getenv("DB_PASS");
        try {
            Class.forName("org.postgresql.Driver");
            this.connection=DriverManager.getConnection(url+dbName,user,pwd);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static ConnectionUtils getInstance() throws SQLException {
        if(connectionUtils==null) {
            connectionUtils=new ConnectionUtils();
        }else if(connectionUtils.getConnection().isClosed()) {
            connectionUtils=new ConnectionUtils();
        }
        return connectionUtils;
    }
}
