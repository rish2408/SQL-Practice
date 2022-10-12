package com.rishabh.usecases;

import java.util.Scanner;

import com.rishabh.bean.Student;
import com.rishabh.dao.StudentDao;
import com.rishabh.dao.StudentDaoImpl;

public class RegisterStudentUsecase2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Student Name");
		String name = sc.next();
		
		System.out.println("Enter Student Marks");
		int marks = sc.nextInt();
		
		System.out.println("Enter Student Email");
		String email = sc.next();
		
		System.out.println("Enter Student Password");
		String password = sc.next();
		
		StudentDao dao = new StudentDaoImpl();
		
		Student st = new Student();
		st.setName(name);
		st.setMarks(marks);
		st.setEmail(email);
		st.setPassword(password);
		
		String result = dao.registerStudent1(st);
		System.out.println(result);
	}
}
