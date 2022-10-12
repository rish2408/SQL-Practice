package com.rishabh.usecases;

import java.util.List;
import java.util.Scanner;

import com.rishabh.bean.StudentDTO;
import com.rishabh.dao.StudentDao;
import com.rishabh.dao.StudentDaoImpl;
import com.rishabh.exception.CourseException;

public class GetStudentByCourseNameUsecase {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Course Name");
		
		String cname = sc.next();
		
		StudentDao dao = new StudentDaoImpl();
		
		try {
			List<StudentDTO> list = dao.getStudentsByCourseName(cname);
			
			list.forEach(c -> {
				System.out.println("Student Roll Number is "+c.getRoll());
				System.out.println("Student Name is "+c.getName());
				System.out.println("Student Email is "+c.getEmail());
				System.out.println("Course Name is "+c.getCname());
				System.out.println("Course Fee is "+c.getFee());
			});
		} catch (CourseException e) {
			System.out.println(e.getMessage());
		}
	}
}
