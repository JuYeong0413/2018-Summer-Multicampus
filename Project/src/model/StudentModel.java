package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.academy.Student;

public class StudentModel {

	String url = "jdbc:oracle:thin:@70.12.115.157:1521:orcl";
	String user = "project";
	String pass = "1234";

	public StudentModel() throws Exception {
		// 1. 드라이버로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}

	public void insertStudent(Student input) throws Exception {
		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장 만들기
		String sql = "INSERT INTO student(tel, name, bday, gender, addr, email, register_date) VALUES(?, ?, ?, ?, ?, ?, sysdate)";

		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, input.getStudentTel());
		st.setString(2, input.getStudentName());
		st.setString(3, input.getStudentBday());
		st.setString(4, input.getStudentGender());
		st.setString(5, input.getStudentAddr());
		st.setString(6, input.getStudentEmail());

		// 5. sql 전송
		st.executeUpdate();

		// 6. 닫기 
		st.close();
		con.close();
	} // end of insertStudent()

	public Student selectByTel(String tel) throws Exception {
		Student s = null;

		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT * FROM student" + " WHERE tel = ?";

		// 4. 전송객체 (PreparedStatement), 물음표 세팅
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, tel);

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6. 결과를 Record의 멤버로 지정
		if(rs.next()) // 각각의 컬럼값을 얻어와서 Customer의 멤버변수 저장
		{
			s = new Student();
			s.setStudentName(rs.getString("name"));
			s.setStudentTel(rs.getString("tel"));
			s.setStudentBday(rs.getString("bday"));
			s.setStudentGender((rs.getString("gender")));
			s.setStudentAddr(rs.getString("addr"));
			s.setStudentEmail(rs.getString("email"));
		}

		// 7. 닫기
		rs.close();
		st.close();
		con.close();

		return s;
	} // end of selectByTel()

	public int updateStudent(Student input) throws Exception {
		int result = 0;

		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장 만들기
		String sql = "UPDATE student SET name = ?, bday = ?, gender = ?, addr = ?, email = ? WHERE tel = ?";

		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, input.getStudentName());
		st.setString(2, input.getStudentBday());
		st.setString(3, input.getStudentGender());
		st.setString(4, input.getStudentAddr());
		st.setString(5, input.getStudentEmail());
		st.setString(6, input.getStudentTel());

		// 5. sql 전송
		result = st.executeUpdate();

		// 6. 닫기 
		st.close();
		con.close();


		return result;
	} // end of updateStudent()
	
	public void deleteStudent(String tel) throws Exception {
		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장 만들기
		String sql = "DELETE FROM student WHERE tel = ?";

		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, tel);

		// 5. sql 전송
		st.executeUpdate();

		// 6. 닫기 
		st.close();
		con.close();
	} // end of deleteStudent()
	
	public ArrayList selectByName(String name) throws Exception {
		ArrayList data = new ArrayList();
		Student s = null;

		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT tel FROM student" + " WHERE name = ?";

		// 4. 전송객체 (PreparedStatement), 물음표 세팅
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, name);

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6. 결과를 Record의 멤버로 지정
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("tel"));
			data.add(temp);
		}

		// 7. 닫기
		rs.close();
		st.close();
		con.close();

		return data;
	} // end of selectByName()
}
