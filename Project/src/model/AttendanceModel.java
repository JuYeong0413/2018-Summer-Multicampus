package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AttendanceModel {
	String url = "jdbc:oracle:thin:@70.12.115.157:1521:orcl";
	String user = "project";
	String pass = "1234";

	public AttendanceModel() throws Exception {
		// 1. 드라이버로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}

	public ArrayList searchByTeacher(String teacher) throws Exception {
		ArrayList data = new ArrayList();

		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		String selWord = "%" + teacher + "%";

		// 3. sql 문장
		String sql = "SELECT num, lecture_name, teacher, day, time, room FROM lecture " +
				" WHERE teacher LIKE '" + selWord + "' ORDER BY num*1";

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
		return data;
	} // end of searchByTeacher()

	public ArrayList searchByLecture(String lecture) throws Exception {
		ArrayList data = new ArrayList();

		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass); 

		String selWord = "%" + lecture + "%";

		// 3. sql 문장
		String sql = "SELECT num, lecture_name, teacher, day, time, room FROM lecture " +
				" WHERE lecture_name LIKE '" + selWord + "' ORDER BY num*1";

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
		return data;
	} // end of searchByLecture()

	public ArrayList searchByNum(String num) throws Exception {

		ArrayList data = new ArrayList();

		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass); 

		// 3. sql 문장
		String sql = "SELECT num, lecture_name, teacher, day, time, room FROM lecture WHERE num = ? ORDER BY num*1"; 

		// 4. sql 전송객체 (물음표 없어도 PreparedStatement)	
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, Integer.parseInt(num));

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
		return data;
	} // end of searchByNum()

	public ArrayList getList(String lecNum) throws Exception {
		ArrayList data = new ArrayList();
		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		//String sql = "SELECT s.name NAME, s.tel TEL FROM student s, lecture l, manage m" + 
		//			 " WHERE s.tel = m.tel AND l.num = m.num AND m.num = " + lecNum + " ORDER BY s.name";
		String sql = "SELECT s.name NAME, s.tel TEL, a.attend ATTEND, a.late LATE, a.absent ABSENT" +
				" FROM student s, lecture l, attendance a" + 
				" WHERE s.tel = a.tel AND l.num = a.num AND a.num = " + lecNum + " ORDER BY s.name";

		// 4. 전송객체 (PreparedStatement), 물음표 세팅
		PreparedStatement st = con.prepareStatement(sql);

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6. 결과를 Record의 멤버로 지정
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("NAME"));
			temp.add(rs.getString("TEL"));
			temp.add(rs.getString("ATTEND"));
			temp.add(rs.getString("LATE"));
			temp.add(rs.getString("ABSENT"));
			data.add(temp);
		}

		return data;
	} // end of getList()

	public ArrayList selectByNumName(String num, String name) throws Exception {
		ArrayList data = new ArrayList();

		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		String selWord = "%" + name + "%";

		// 3. sql 문장
		String sql = "SELECT num, lecture_name, teacher, day, time, room FROM lecture " +
				" WHERE num = ? AND lecture_name LIKE '" + selWord + "' ORDER BY num*1";

		// 4. sql 전송객체 (물음표 없어도 PreparedStatement)	
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, Integer.parseInt(num));

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
		return data;
	} // end of searchByNumName()

	public ArrayList selectByNumTeacher(String num, String teacher) throws Exception {
		ArrayList data = new ArrayList();

		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		String selWord = "%" + teacher + "%";

		// 3. sql 문장
		String sql = "SELECT num, lecture_name, teacher, day, time, room FROM lecture " +
				" WHERE num = ? AND teacher LIKE '" + selWord + "' ORDER BY num*1";

		// 4. sql 전송객체 (물음표 없어도 PreparedStatement)	
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, Integer.parseInt(num));

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
		return data;
	} // end of searchByNumTeacher()

	public ArrayList selectByAll(String num, String name, String teacher) throws Exception {
		ArrayList data = new ArrayList();

		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		String selWord = "%" + name + "%";
		String selWord2 = "%" + teacher + "%";

		// 3. sql 문장
		String sql = "SELECT num, lecture_name, teacher, day, time, room FROM lecture " +
				" WHERE num = ? AND lecture_name LIKE '" + selWord + "' AND teacher LIKE '" + selWord2 + "' ORDER BY num*1";

		// 4. sql 전송객체 (물음표 없어도 PreparedStatement)	
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, Integer.parseInt(num));

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
		return data;
	} // end of selectByAll()

	public ArrayList selectByNameTeacher(String name, String teacher) throws Exception {
		ArrayList data = new ArrayList();

		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		String selWord = "%" + name + "%";
		String selWord2 = "%" + teacher + "%";

		// 3. sql 문장
		String sql = "SELECT num, lecture_name, teacher, day, time, room FROM lecture " +
				" WHERE lecture_name LIKE '" + selWord + "' AND teacher LIKE '" + selWord2 + "' ORDER BY num*1";

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
		return data;
	} // end of selectByNameTeacher()

	public int getAttend(int lecNum) throws Exception {
		int attend = 0;
		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT COUNT(*) ATTEND FROM attendance" + " WHERE num = ? AND ATTEND = 'Y'";

		// 4. 전송객체 (PreparedStatement), 물음표 세팅
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, lecNum);

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6
		if(rs.next()) {
			attend = rs.getInt("ATTEND");
		}

		// 7. 닫기
		rs.close();
		st.close();
		con.close();

		return attend;
	} // end of getAttend()

	public int getLate(int lecNum) throws Exception {
		int late = 0;
		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT COUNT(*) LATE FROM attendance" + " WHERE num = ? AND late = 'Y'";

		// 4. 전송객체 (PreparedStatement), 물음표 세팅
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, lecNum);

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6
		if(rs.next()) {
			late = rs.getInt("LATE");
		}

		// 7. 닫기
		rs.close();
		st.close();
		con.close();

		return late;
	} // end of getLate()

	public int getAbsent(int lecNum) throws Exception {
		int absent = 0;
		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT COUNT(*) ABSENT FROM attendance" + " WHERE num = ? AND absent = 'Y'";

		// 4. 전송객체 (PreparedStatement), 물음표 세팅
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, lecNum);

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6
		if(rs.next()) {
			absent = rs.getInt("ABSENT");
		}

		// 7. 닫기
		rs.close();
		st.close();
		con.close();

		return absent;
	} // end of getAbsent()

	public int getTotal(int lecNum) throws Exception {
		int total = 0;
		// 2. 연결 객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장
		String sql = "SELECT COUNT(*) TOTAL FROM attendance" + " WHERE num = ?";

		// 4. 전송객체 (PreparedStatement), 물음표 세팅
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, lecNum);

		// 5. executeQuery()로 전송
		ResultSet rs = st.executeQuery();

		// 6
		if(rs.next()) {
			total = rs.getInt("TOTAL");
		}

		// 7. 닫기
		rs.close();
		st.close();
		con.close();

		return total;
	} // end of getTotal()

	public int updateAttendance(int lNum, int classNum) throws Exception {
		int result = 0;

		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// 3. sql 문장 만들기
		String sql = "UPDATE attendance SET attend = ?, late = ?, absent = ?, class_num = ? WHERE num = ? AND tel = ?";

		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement st = con.prepareStatement(sql);
		
		//while(data.get) {
		//st.setString(1,);
		//st.setString(2, input.getDay());
		//st.setInt(3, input.getTime());
		st.setInt(4, classNum);
		st.setInt(5, lNum);
		//st.setString(6, tel);

		// 5. sql 전송
		result = st.executeUpdate();
		//}
		// 6. 닫기 
		st.close();
		con.close();

		return result;
	} // end of updateAttendance()
}