package com.rishabh.usecases;

import java.util.List;

import com.rishabh.bean.Student;
import com.rishabh.dao.StudentDao;
import com.rishabh.dao.StudentDaoImpl;
import com.rishabh.exception.StudentException;

public class GetAllStudentUsecase {

	public static void main(String[] args) {
		
		StudentDao dao = new StudentDaoImpl();
		
		try {
			List<Student> st = dao.getAllStudentDetails();
			
			st.forEach(s->{
				System.out.println("Student Roll Number is "+s.getRoll());
				System.out.println("Student Name is "+s.getName());
				System.out.println("Student Marks is "+s.getMarks());
				System.out.println("=========================================");
			});
			
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		}
	}
}
