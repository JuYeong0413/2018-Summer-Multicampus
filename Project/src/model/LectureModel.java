package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.academy.Lecture;

public class LectureModel {
	String url = "jdbc:oracle:thin:@70.12.115.157:1521:orcl";
	String user = "project";
	String pass = "1234";

	public LectureModel() throws Exception {
		// 1. 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}
	
	public void insertLecture(Lecture input) throws Exception {
		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "INSERT INTO lecture(num, lecture_name, teacher, day, time, room, capacity) VALUES(seq_lecture_num.NEXTVAL, ?, ?, ?, ?, ?, ?)";

		// 4. sql 전송객체 (PreparedStatement)	
		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, input.getLectureName());
		st.setString(2, input.getTeacher());
		st.setString(3, input.getDay());
		st.setInt(4, input.getTime());
		st.setInt(5, input.getRoom());
		st.setInt(6, input.getCapacity());

		// 5. sql 전송
		st.executeUpdate();

		// 6. 닫기
		st.close();
		con.close();
	} // end of insertLecture()

	public ArrayList searchLecture(int sel, String word) throws Exception {
		String[] colName = { "lecture_name", "teacher", "room" };
		ArrayList data = new ArrayList();

		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass); 

		// 3. sql 문장
		String selWord = "%" + word + "%";
		String sql = "SELECT num, lecture_name, teacher, day, time, room FROM lecture WHERE " + colName[sel] + " LIKE '" + selWord + "' ORDER BY num*1";

		// 4. sql 전송객체 (물음표 없어도 PreparedStatement)	
		PreparedStatement st = con.prepareStatement(sql);

		// 5. sql 전송
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("num"));
			temp.add(rs.getString("lecture_name"));
			temp.add(rs.getString("teacher"));
			temp.add(rs.getString("day"));
			temp.add(rs.getString("time"));
			temp.add(rs.getString("room"));
			data.add(temp);
		}

		// 6. 닫기
		st.close();
		con.close();

		return data;
	} // end of searchLecture()

	public Lecture selectByPk(String lecNum) throws Exception {
		Lecture lec = new Lecture();
		Connection con = null;

		// 2. 연결객체
		con = DriverManager.getConnection(url, user, pass);

		// 3. SQL 문장
		String sql = "SELECT * FROM lecture WHERE num = " + lecNum;

		// 4. 전송객체
		PreparedStatement st = con.prepareStatement(sql);

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6. 결과를 Record의 멤버로 지정
		if(rs.next())
		{
			lec.setLectureNum(rs.getInt("num"));
			lec.setLectureName(rs.getString("lecture_name"));
			lec.setTeacher(rs.getString("teacher"));
			lec.setDay(rs.getString("day"));
			lec.setTime(rs.getInt("time"));
			lec.setRoom(rs.getInt("room"));
			lec.setCapacity(rs.getInt("capacity"));
		}

		// 7. 닫기
		rs.close();
		st.close();
		con.close();

		return lec;
	} // end of selectByPk()

	public int modifyLecture(Lecture input) throws Exception {
		int result = 0;

		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장 만들기
		String sql = "UPDATE lecture SET lecture_name = ?, teacher = ?, day = ?, time = ?, room = ? WHERE num = " + input.getLectureNum();

		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, input.getLectureName());
		st.setString(2, input.getTeacher());
		st.setString(3, input.getDay());
		st.setInt(4, input.getTime());
		st.setInt(5, input.getRoom());

		// 5. sql 전송
		result = st.executeUpdate();

		// 6. 닫기 
		st.close();
		con.close();

		return result;
	} // end of modifyLecture()

	public void deleteLecture(String lecNum) throws Exception {
		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장 만들기
		String sql = "DELETE FROM lecture WHERE num = ?";

		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, Integer.parseInt(lecNum));

		// 5. sql 전송
		st.executeUpdate();

		// 6. 닫기 
		st.close();
		con.close();
	} // end of deleteLecture()

	public void deleteAttendance(String lecNum) throws Exception{
		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장 만들기
		String sql = "DELETE FROM attendance WHERE num = ?";

		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, Integer.parseInt(lecNum));

		// 5. sql 전송
		st.executeUpdate();

		// 6. 닫기 
		st.close();
		con.close();
	} // end of deleteAttendance()

	public void deleteManage(String lecNum) throws Exception {
		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장 만들기
		String sql = "DELETE FROM manage WHERE num = ?";

		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, Integer.parseInt(lecNum));

		// 5. sql 전송
		st.executeUpdate();

		// 6. 닫기 
		st.close();
		con.close();
	} // end of deleteManage()

	public void registerLecture(String lecNum, String tel) throws Exception {
		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "INSERT INTO manage(num, tel) VALUES(?, ?)";

		// 4. sql 전송객체 (PreparedStatement)	
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, Integer.parseInt(lecNum));
		st.setString(2, tel);

		// 5. sql 전송
		st.executeUpdate();

		// 6. 닫기
		st.close();
		con.close();
	} // end of registerLecture()

	public boolean searchTel(String tel) throws Exception {
		boolean hasTel = true;
		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT tel FROM student" + " WHERE tel = ?";

		// 4. 전송객체 (PreparedStatement), 물음표 세팅
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, tel);

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6
		if(!rs.next()) {
			hasTel = false;
		}

		// 7. 닫기
		rs.close();
		st.close();
		con.close();

		return hasTel;
	} // end of selectByTel()

	public int checkCapacity(String lecNum) throws Exception {
		int capacity = 0;
		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT capacity FROM lecture" + " WHERE num = ?";

		// 4. 전송객체 (PreparedStatement), 물음표 세팅
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, Integer.parseInt(lecNum));

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
	} // end of checkCapacity()

	public boolean checkLecture(String tel, String lecNum) throws Exception {
		boolean lecture = true;
		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT num FROM manage" + " WHERE tel = ? AND num = ?";

		// 4. 전송객체 (PreparedStatement), 물음표 세팅
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, tel);
		st.setInt(2, Integer.parseInt(lecNum));

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6
		if(rs.next()) {
			lecture = false;
		}

		// 7. 닫기
		rs.close();
		st.close();
		con.close();

		return lecture;
	} // end of checkLecture()

	public ArrayList getLecture(String tel) throws Exception {
		ArrayList data = new ArrayList();
		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT l.day DAY, l.time TIME FROM lecture l, manage m" + 
				" WHERE l.num = m.num AND m.tel = ?" + 
				" ORDER BY l.num";

		// 4. 전송객체 (PreparedStatement), 물음표 세팅
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, tel);

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("DAY"));
			temp.add(rs.getString("TIME"));
			data.add(temp);
		}

		// 7. 닫기
		rs.close();
		st.close();
		con.close();

		return data;
	} // end of getLecture()

	public ArrayList getTime(String lecNum, String day, String time) throws Exception {
		ArrayList data = new ArrayList();
		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT day, time FROM lecture WHERE num = ?";

		// 4. 전송객체 (PreparedStatement), 물음표 세팅
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, Integer.parseInt(lecNum));

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6
		if(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("day"));
			temp.add(rs.getString("time"));
			data.add(temp);
		}

		// 7. 닫기
		rs.close();
		st.close();
		con.close();

		return data;
	} // end of getTime()

	public int selectCapacity(String lecNum) throws Exception {
		int capacity = 0;
		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT capacity FROM lecture" + " WHERE num = " + Integer.parseInt(lecNum);

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
	}

	public int increaseCapacity(String lecNum) throws Exception {
		int result = 0;

		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장 만들기
		String sql = "UPDATE lecture SET capacity = ? WHERE num = " + Integer.parseInt(lecNum);

		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement st = con.prepareStatement(sql);

		st.setInt(1, selectCapacity(lecNum) + 1);

		// 5. sql 전송
		result = st.executeUpdate();

		// 6. 닫기 
		st.close();
		con.close();

		return result;

	} // end of increaseCapacity()

	public void register(String lecNum, String tel) throws Exception {
		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "INSERT INTO attendance(data_num, num, tel, attend, late, absent) VALUES(seq_data_num.NEXTVAL, ?, ?, 'N', 'N', 'Y')";

		// 4. sql 전송객체 (PreparedStatement)	
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, Integer.parseInt(lecNum));
		st.setString(2, tel);

		// 5. sql 전송
		st.executeUpdate();

		// 6. 닫기
		st.close();
		con.close();
	} // end of register()
}
