package org.shopping.JavaCode;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class Connect {
	public static BasicDataSource Connect()
	{
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUsername("sandhya");
		dataSource.setPassword("2611798");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		return dataSource;
		
	}
}
