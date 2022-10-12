package com.rishabh.utility;

import java.sql.Connection;

public class Demo {

	public static void main(String[] args) {
		
		Connection conn = DBUtil.getConnection();
		
		System.out.println(conn);
		
		if(conn!=null)
			System.out.println("Connected");
	}
}
