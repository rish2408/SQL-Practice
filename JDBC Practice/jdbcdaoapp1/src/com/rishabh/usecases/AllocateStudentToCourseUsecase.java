package com.rishabh.usecases;

import java.util.Scanner;

import com.rishabh.dao.StudentDao;
import com.rishabh.dao.StudentDaoImpl;
import com.rishabh.exception.CourseException;
import com.rishabh.exception.StudentException;

public class AllocateStudentToCourseUsecase {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Course Id");
		int cid = sc.nextInt();
		
		System.out.println("Enter Roll Number");
		int roll = sc.nextInt();
		
		StudentDao dao = new StudentDaoImpl();
		
		try {
			String result = dao.registerStudentInsideACourse(cid, roll);
			System.out.println(result);
		} catch (StudentException e) {

			System.out.println(e.getMessage());
		} catch (CourseException e) {

			System.out.println(e.getMessage());
		}
	}
}
