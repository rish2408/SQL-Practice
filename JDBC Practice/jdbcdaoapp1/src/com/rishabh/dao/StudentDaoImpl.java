package com.rishabh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rishabh.bean.Student;
import com.rishabh.bean.StudentDTO;
import com.rishabh.exception.CourseException;
import com.rishabh.exception.StudentException;
import com.rishabh.utility.DBUtil;

public class StudentDaoImpl implements StudentDao {

	@Override
	public String registerStudent(String name, int marks, String email, String password) {

		String msg = "not inserted yet....";
		
		try(Connection conn = DBUtil.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("insert into student(name,marks,email,password) values(?,?,?,?)");
			
			ps.setString(1, name);
			ps.setInt(2, marks);
			ps.setString(3, email);
			ps.setString(4, password);
			
			int result = ps.executeUpdate();
			
			if(result>0)
				msg = "Student Registered Successfully";
			
		} catch (SQLException e) {
			msg = e.getMessage();
		}
		
		return msg;
	}

	@Override
	public String registerStudent1(Student student) {

		String msg1 = "Not Inserted Yet";
		
		try(Connection conn = DBUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("insert into student(name,marks,email,password) values(?,?,?,?)");
		
			ps.setString(1, student.getName());
			ps.setInt(2, student.getMarks());
			ps.setString(3, student.getEmail());
			ps.setString(4, student.getPassword());
			
			int result1 = ps.executeUpdate();
			
			if(result1>0)
				msg1 = "Student Registered Successfully";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			msg1 = e.getMessage();
		}
		
		return msg1;
	}

	@Override
	public Student getStudentByRoll(int roll) throws StudentException {
		
		Student student = null;
		
		try(Connection conn = DBUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from student where roll = ?");
			
			ps.setInt(1, roll);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				int r = rs.getInt("roll");
				String n = rs.getString("name");
				int m = rs.getInt("marks");
				String e = rs.getString("email");
				String p = rs.getString("password");
				
				student = new Student(r, n, m, e, p);
			}else {
				throw new StudentException("Student Doesn't Exist With roll "+roll);
			}
			
		} catch (SQLException e) {
			throw new StudentException(e.getMessage());
		}
		
		return student;
		
	}

	@Override
	public Student loginStudent(String username, String password) throws StudentException {
		
		Student student = null;
		
		try(Connection conn = DBUtil.getConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from student where email = ? AND password = ?");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				int r = rs.getInt("roll");
				String n = rs.getString("name");
				int m = rs.getInt("marks");
				String e = rs.getString("email");
				String p = rs.getString("password");
				
				student = new Student(r, n, m, e, p);
			}else {
				throw new StudentException("Invalid Username and Password");
			}
			
		} catch (SQLException e) {
			throw new StudentException(e.getMessage());
		}
		
		return student;
		
	}

	@Override
	public List<Student> getAllStudentDetails() throws StudentException {
		
		List<Student> students = new ArrayList<>();
		
		try(Connection conn = DBUtil.getConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from student");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				int r = rs.getInt("roll");
				String n = rs.getString("name");
				int m = rs.getInt("marks");
				String e = rs.getString("email");
				String p = rs.getString("password");
				
				Student student = new Student(r, n, m, e, p);
				
				students.add(student);
			}
			
		} catch (SQLException e) {
			throw new StudentException(e.getMessage());
		}
		
		if(students.size() == 0)
			System.out.println("Student List Empty");
		
		return students;
	}

	@Override
	public String registerStudentInsideACourse(int cid, int roll) throws StudentException, CourseException {
		
		String message = "Not Registered..!!";
		
		try(Connection conn = DBUtil.getConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from student where roll = ?");
			
			ps.setInt(1, roll);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				PreparedStatement ps2 = conn.prepareStatement("select * from course where cid = ?");
				
				ps2.setInt(1, cid);
				
				ResultSet rs2 = ps2.executeQuery();
				
				if(rs2.next())
				{
					PreparedStatement ps3 = conn.prepareStatement("insert into course_student values(?,?)");
				
					ps3.setInt(1, cid);
					ps3.setInt(2, roll);
					
					int result = ps3.executeUpdate();
					
					if(result>0)
						message = "Student Registered to the course Successfully";
					else
						throw new StudentException("Technical Error..!");
				}else {
					throw new CourseException("Invalid Course");
				}
			}else {
				throw new StudentException("Invalid Student");
			}
			
		} catch (SQLException e) {
			throw new StudentException(e.getMessage());
		}
		
		return message;
	}

	@Override
	public List<StudentDTO> getStudentsByCourseName(String cname) throws CourseException {

		List<StudentDTO> studentsdtos = new ArrayList<>();
		
		try(Connection conn = DBUtil.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select s.roll,s.name,s.email, c.cname,c.fee from student s INNER JOIN course c INNER JOIN course_student cs ON s.roll = cs.roll\r\n"
					+ "AND c.cid = cs.cid AND c.cname = ?");
			
			ps.setString(1, cname);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				int r = rs.getInt("roll");
				String n = rs.getString("name");
				String e = rs.getString("email");
				
				String c = rs.getString("cname");
				int f = rs.getInt("fee");
				
				StudentDTO studentdto = new StudentDTO(r, n, e, c, f);
				
				studentsdtos.add(studentdto);
			}
			
		} catch (SQLException e) {
			throw new CourseException(e.getMessage());
		}
		
		if(studentsdtos.isEmpty())
			throw new CourseException("No Student found in that couse.");
		
		return studentsdtos;
	}

}
