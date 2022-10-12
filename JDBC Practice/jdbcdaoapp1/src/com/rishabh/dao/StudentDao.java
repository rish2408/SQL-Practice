package com.rishabh.dao;

import java.util.List;

import com.rishabh.bean.Student;
import com.rishabh.bean.StudentDTO;
import com.rishabh.exception.CourseException;
import com.rishabh.exception.StudentException;

public interface StudentDao {

	public String registerStudent(String name, int marks, String email, String password);
	
	public String registerStudent1(Student student);
	
	public Student getStudentByRoll(int roll) throws StudentException;
	
	public Student loginStudent(String username,String password) throws StudentException;
	
	public List<Student> getAllStudentDetails() throws StudentException;
	
	public String registerStudentInsideACourse(int cid,int roll) throws StudentException,CourseException;

	public List<StudentDTO> getStudentsByCourseName(String cname) throws CourseException;
}
