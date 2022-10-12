package com.rishabh.usecases;

import java.util.Scanner;

import com.rishabh.bean.Student;
import com.rishabh.dao.StudentDao;
import com.rishabh.dao.StudentDaoImpl;
import com.rishabh.exception.StudentException;

public class GetStudentUsecase1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Your Roll Number");
		
		int roll = sc.nextInt();
		
		StudentDao dao = new StudentDaoImpl();
		
		
		try {
			Student student = dao.getStudentByRoll(roll);
			System.out.println(student);
		} catch (StudentException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
}
