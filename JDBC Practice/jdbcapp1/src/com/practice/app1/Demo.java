package com.practice.app1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo {

	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/jdbcpractice";
		
		try(Connection conn = DriverManager.getConnection(url, "root", "root")) {
			
//			if(conn!=null)
//				System.out.println("Connected");
			
		PreparedStatement ps = conn.prepareStatement("select name,marks from student where marks<900");
		
		ResultSet rs = ps.executeQuery();
		
		boolean flag = true;
		
		while(rs.next())
		{
			flag = false;
			String n = rs.getString("name");
			int m = rs.getInt("marks");
			
			System.out.println("Name is "+n);
			System.out.println("Marks is "+m);
			System.out.println("=========================");
		}
		
		if(flag)
			System.out.println("No Record Available");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
