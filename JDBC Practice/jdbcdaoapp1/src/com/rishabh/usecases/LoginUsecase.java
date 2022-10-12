package com.rishabh.usecases;

import java.util.Scanner;

import com.rishabh.bean.Student;
import com.rishabh.dao.StudentDao;
import com.rishabh.dao.StudentDaoImpl;
import com.rishabh.exception.StudentException;

public class LoginUsecase {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Username");
		String username = sc.next();
		
		System.out.println("Enter Password");
		String password = sc.next();
		
		StudentDao dao = new StudentDaoImpl();
		
		try {
			Student student = dao.loginStudent(username, password);
			
			System.out.println("Welcome "+student.getName());
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
