package model;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class StatsModel {
	static String url = "jdbc:oracle:thin:@70.12.115.157:1521:orcl";
	static String user = "project";
	static String pass = "1234";

	public StatsModel() throws Exception {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public void getStudentGender() {
		JFrame frame = new JFrame();

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		try {
			// 2. 연결객체 얻어오기
			Connection con = DriverManager.getConnection(url, user, pass);

			// 3. SQL 문장 만들기
			String sql = "SELECT count(*) COUNT, gender GENDER FROM student GROUP BY gender";

			// 4. 전송객체
			Statement st = con.createStatement();

			// 5. 전송
			ResultSet rs = st.executeQuery(sql);

			while(rs.next())
			{
				dataset.addValue(rs.getInt("COUNT"), "COUNT", rs.getString("GENDER"));
			}

			JFreeChart chart = ChartFactory.createBarChart("남녀 학생 수", "성별", "학생 수", dataset, 
					PlotOrientation.VERTICAL , true, true, false);

			chart.setBackgroundPaint(Color.WHITE);
			chart.setBorderPaint(Color.BLUE);

			// font
			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			Font labelFont = null;

			labelFont = chart.getTitle().getFont();
			Font f = new Font("굴림", labelFont.getStyle(), labelFont.getSize());
			chart.getTitle().setFont(f);

			labelFont = plot.getDomainAxis().getLabelFont();
			plot.getDomainAxis().setLabelFont(f);

			labelFont = plot.getDomainAxis().getTickLabelFont();
			plot.getDomainAxis().setTickLabelFont(f);

			labelFont = plot.getRangeAxis().getLabelFont();
			plot.getRangeAxis().setLabelFont(f);

			labelFont = plot.getRangeAxis().getTickLabelFont();
			plot.getRangeAxis().setTickLabelFont(f);

			chart.getLegend().setItemFont(f);


			ChartPanel chartPanel = new ChartPanel(chart);

			frame.getContentPane().add(chartPanel);
			frame.setSize(700, 500);
			frame.setVisible(true);
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// 6. 닫기
			rs.close();
			st.close();
			con.close();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void getNewStudent() {
		JFrame frame = new JFrame();

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		try {
			// 2. 연결객체 얻어오기
			Connection con = DriverManager.getConnection(url, user, pass);

			// 3. SQL 문장 만들기
			String sql = "SELECT to_char(register_date, 'YYYYMM') DAY, count(*) COUNT" + 
					" FROM student GROUP BY to_char(register_date, 'YYYYMM')";

			// 4. 전송객체
			Statement st = con.createStatement();

			// 5. 전송
			ResultSet rs = st.executeQuery(sql);

			while(rs.next())
			{
				dataset.addValue(rs.getInt("COUNT"), "COUNT", rs.getString("DAY"));
			}

			JFreeChart chart = ChartFactory.createBarChart("월별 신규등록 학생 수", "날짜", "학생 수", dataset, 
					PlotOrientation.VERTICAL , true, true, false);

			chart.setBackgroundPaint(Color.WHITE);
			chart.setBorderPaint(Color.BLUE);

			// font
			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			Font labelFont = null;

			labelFont = chart.getTitle().getFont();
			Font f = new Font("굴림", labelFont.getStyle(), labelFont.getSize());
			chart.getTitle().setFont(f);

			labelFont = plot.getDomainAxis().getLabelFont();
			plot.getDomainAxis().setLabelFont(f);

			labelFont = plot.getDomainAxis().getTickLabelFont();
			plot.getDomainAxis().setTickLabelFont(f);

			labelFont = plot.getRangeAxis().getLabelFont();
			plot.getRangeAxis().setLabelFont(f);

			labelFont = plot.getRangeAxis().getTickLabelFont();
			plot.getRangeAxis().setTickLabelFont(f);

			chart.getLegend().setItemFont(f);


			ChartPanel chartPanel = new ChartPanel(chart);

			frame.getContentPane().add(chartPanel);
			frame.setSize(700, 500);
			frame.setVisible(true);
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// 6. 닫기
			rs.close();
			st.close();
			con.close();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void getLecture() {
		JFrame frame = new JFrame();

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		try {
			// 2. 연결객체 얻어오기
			Connection con = DriverManager.getConnection(url, user, pass);

			// 3. SQL 문장 만들기
			String sql = "SELECT l.lecture_name LECTURE, count(*) COUNT FROM lecture l, manage m" + 
					" WHERE l.num = m.num GROUP BY l.lecture_name ORDER BY l.lecture_name";
			//System.out.println(sql);

			// 4. 전송객체
			Statement st = con.createStatement();

			// 5. 전송
			ResultSet rs = st.executeQuery(sql);

			while(rs.next())
			{
				dataset.addValue(rs.getInt("COUNT"), "LECTURE", rs.getString("LECTURE"));
			}

			JFreeChart chart = ChartFactory.createBarChart("수업별 수강생 수", "수업명", "수강인원", dataset, 
					PlotOrientation.VERTICAL , true, true, false);

			chart.setBackgroundPaint(Color.WHITE);
			chart.setBorderPaint(Color.BLUE);

			// font
			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			Font labelFont = null;

			labelFont = chart.getTitle().getFont();
			Font f = new Font("굴림", labelFont.getStyle(), labelFont.getSize());
			chart.getTitle().setFont(f);

			labelFont = plot.getDomainAxis().getLabelFont();
			plot.getDomainAxis().setLabelFont(f);

			labelFont = plot.getDomainAxis().getTickLabelFont();
			plot.getDomainAxis().setTickLabelFont(f);

			labelFont = plot.getRangeAxis().getLabelFont();
			plot.getRangeAxis().setLabelFont(f);

			labelFont = plot.getRangeAxis().getTickLabelFont();
			plot.getRangeAxis().setTickLabelFont(f);

			chart.getLegend().setItemFont(f);

			ChartPanel chartPanel = new ChartPanel(chart);

			frame.getContentPane().add(chartPanel);
			frame.setSize(1000, 500);
			frame.setVisible(true);
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// 6. 닫기
			rs.close();
			st.close();
			con.close();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void getTL() {
		JFrame frame = new JFrame();

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		try {
			// 2. 연결객체 얻어오기
			Connection con = DriverManager.getConnection(url, user, pass);

			// 3. SQL 문장 만들기
			String sql = "SELECT teacher TEACHER, count(*) COUNT FROM lecture GROUP BY teacher ORDER BY teacher";
			//System.out.println(sql);

			// 4. 전송객체
			Statement st = con.createStatement();

			// 5. 전송
			ResultSet rs = st.executeQuery(sql);

			while(rs.next())
			{
				dataset.addValue(rs.getInt("COUNT"), "TEACHER", rs.getString("TEACHER"));
			}

			JFreeChart chart = ChartFactory.createBarChart("강사별 담당 강의 개수", "강사명", "강의 개수", dataset, 
					PlotOrientation.VERTICAL , true, true, false);

			chart.setBackgroundPaint(Color.WHITE);
			chart.setBorderPaint(Color.BLUE);

			// font
			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			Font labelFont = null;

			labelFont = chart.getTitle().getFont();
			Font f = new Font("굴림", labelFont.getStyle(), labelFont.getSize());
			chart.getTitle().setFont(f);

			labelFont = plot.getDomainAxis().getLabelFont();
			plot.getDomainAxis().setLabelFont(f);

			labelFont = plot.getDomainAxis().getTickLabelFont();
			plot.getDomainAxis().setTickLabelFont(f);

			labelFont = plot.getRangeAxis().getLabelFont();
			plot.getRangeAxis().setLabelFont(f);

			labelFont = plot.getRangeAxis().getTickLabelFont();
			plot.getRangeAxis().setTickLabelFont(f);

			chart.getLegend().setItemFont(f);

			ChartPanel chartPanel = new ChartPanel(chart);

			frame.getContentPane().add(chartPanel);
			frame.setSize(700, 500);
			frame.setVisible(true);
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// 6. 닫기
			rs.close();
			st.close();
			con.close();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void getTS() {
		JFrame frame = new JFrame();

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		try {
			// 2. 연결객체 얻어오기
			Connection con = DriverManager.getConnection(url, user, pass);

			// 3. SQL 문장 만들기
			String sql = "SELECT l.teacher TEACHER, count(*) COUNT FROM lecture l, manage m" + 
					" WHERE l.num = m.num GROUP BY teacher ORDER BY teacher";
			//System.out.println(sql);

			// 4. 전송객체
			Statement st = con.createStatement();

			// 5. 전송
			ResultSet rs = st.executeQuery(sql);

			while(rs.next())
			{
				dataset.addValue(rs.getInt("COUNT"), "TEACHER", rs.getString("TEACHER"));
			}

			JFreeChart chart = ChartFactory.createBarChart("강사별 수강생 수", "강사명", "수강인원", dataset, 
					PlotOrientation.VERTICAL , true, true, false);

			chart.setBackgroundPaint(Color.WHITE);
			chart.setBorderPaint(Color.BLUE);

			// font
			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			Font labelFont = null;

			labelFont = chart.getTitle().getFont();
			Font f = new Font("돋움", labelFont.getStyle(), labelFont.getSize());
			chart.getTitle().setFont(f);

			labelFont = plot.getDomainAxis().getLabelFont();
			plot.getDomainAxis().setLabelFont(f);

			labelFont = plot.getDomainAxis().getTickLabelFont();
			plot.getDomainAxis().setTickLabelFont(f);

			labelFont = plot.getRangeAxis().getLabelFont();
			plot.getRangeAxis().setLabelFont(f);

			labelFont = plot.getRangeAxis().getTickLabelFont();
			plot.getRangeAxis().setTickLabelFont(f);

			chart.getLegend().setItemFont(f);


			ChartPanel chartPanel = new ChartPanel(chart);

			frame.getContentPane().add(chartPanel);
			frame.setSize(700, 500);
			frame.setVisible(true);
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// 6. 닫기
			rs.close();
			st.close();
			con.close();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
