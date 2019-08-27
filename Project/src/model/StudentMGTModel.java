package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.academy.Student;

public class StudentMGTModel {
	String url = "jdbc:oracle:thin:@70.12.115.157:1521:orcl";
	String user = "project";
	String pass = "1234";

	public StudentMGTModel() throws Exception {
		// 1. 드라이버로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}

	public Student selectByTel(String tel) throws Exception {
		Student s = null;

		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT tel, name FROM student" + " WHERE tel = ?";

		// 4. 전송객체 (PreparedStatement), 물음표 세팅
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, tel);

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6. 결과를 Record의 멤버로 지정
		if(rs.next()) // 각각의 컬럼값을 얻어와서 Customer의 멤버변수 저장
		{
			s = new Student();
			s.setStudentTel(rs.getString("tel"));
			s.setStudentName(rs.getString("name"));
		}

		// 7. 닫기
		rs.close();
		st.close();
		con.close();

		return s;
	} // end of selectByTel()

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

	public Student searchList(String tel, String name) throws Exception {
		Student s = null;

		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT tel, name FROM student" + " WHERE tel = ? AND name = ?";

		// 4. 전송객체 (PreparedStatement), 물음표 세팅
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, tel);
		st.setString(2, name);

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6. 결과를 Record의 멤버로 지정
		if(rs.next()) // 각각의 컬럼값을 얻어와서 Customer의 멤버변수 저장
		{
			s = new Student();
			s.setStudentTel(rs.getString("tel"));
			s.setStudentName(rs.getString("name"));
		}

		// 7. 닫기
		rs.close();
		st.close();
		con.close();

		return s;
	} // end of searchList()

	public ArrayList getList(String tel) throws Exception {
		ArrayList data = new ArrayList();
		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT l.num NUM, l.lecture_name LECTURE_NAME, l.teacher TEACHER, l.day DAY, l.time TIME, l.room ROOM" +
				" FROM lecture l, manage m WHERE l.num = m.num AND m.tel = ?" + 
				" ORDER BY m.num*1";

		// 4. 전송객체 (PreparedStatement), 물음표 세팅
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, tel);

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6. 결과를 Record의 멤버로 지정
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("NUM"));
			temp.add(rs.getString("LECTURE_NAME"));
			temp.add(rs.getString("TEACHER"));
			temp.add(rs.getString("DAY"));
			temp.add(rs.getString("TIME"));
			temp.add(rs.getString("ROOM"));
			data.add(temp);
		}

		return data;
	} // end of getList()

	public ArrayList getTimeTable(String tel) throws Exception {
		ArrayList data = new ArrayList();
		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT l.day, l.time FROM student s, lecture l, manage m " +
				" WHERE s.tel = m.tel AND m.num = l.num AND m.tel = ?";

		// 4. 전송객체 (PreparedStatement), 물음표 세팅
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, tel);

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6. 결과를 Record의 멤버로 지정
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("DAY"));
			temp.add(rs.getString("TIME"));
			data.add(temp);
		}

		return data;
	} // end of gettimeTable()

	public void dropLecture(int lecNum) throws Exception {
		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장 만들기
		String sql = "DELETE FROM manage WHERE num = " + lecNum;

		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement st = con.prepareStatement(sql);
		//st.setInt(1, Integer.parseInt(lecNum));

		// 5. sql 전송
		st.executeUpdate();

		// 6. 닫기 
		st.close();
		con.close();
	} // end of dropLecture()

	public int decreaseCapacity(int lecNum) throws Exception {
		int result = 0;

		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장 만들기
		String sql = "UPDATE lecture SET capacity = ? WHERE num = " + lecNum;

		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement st = con.prepareStatement(sql);

		st.setInt(1, selectCapacity(lecNum) - 1);

		// 5. sql 전송
		result = st.executeUpdate();

		// 6. 닫기 
		st.close();
		con.close();

		return result;

	} // end of decreaseCapacity()

	public int selectCapacity(int lecNum) throws Exception {
		int capacity = 0;
		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT capacity FROM lecture" + " WHERE num = " + lecNum;

		// 4. 전송객체 (PreparedStatement)
		PreparedStatement st = con.prepareStatement(sql);

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6
		if(rs.next()) {
			capacity = rs.getInt("capacity");
		}

		// 7. 닫기
		rs.close();
		st.close();
		con.close();

		return capacity;
	} // end of selectCapacity

	public void registerCancel(int lecNum, String tel) throws Exception {
		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장 만들기
		String sql = "DELETE FROM attendance WHERE tel = ? AND num = " + lecNum;

		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, tel);

		// 5. sql 전송
		st.executeUpdate();

		// 6. 닫기 
		st.close();
		con.close();
	} // end of registerCancel()
}
