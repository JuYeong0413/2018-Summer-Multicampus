package test;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class EmpSal {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@70.12.115.157:1521:orcl";
		String user = "scott";
		String pass = "tiger";
	

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 연결객체 얻어오기
			Connection con = DriverManager.getConnection(url, user, pass);

			// 3. SQL 문장 만들기
			String sql = "SELECT ename, sal FROM emp";

			// 4. 전송객체
			Statement st = con.createStatement();

			// 5. 전송
			ResultSet rs = st.executeQuery(sql);

			while(rs.next())
			{
				//System.out.print(rs.getString("ENAME") + "\t");
				//System.out.print(rs.getString("SAL") + "\t");
				//System.out.println();

				dataset.addValue(Integer.parseInt(rs.getString("SAL")), "ENAME", rs.getString("ENAME"));
			}

			JFreeChart chart = ChartFactory.createBarChart("Salary of Employees", "ENAME", "SAL", dataset, 
					PlotOrientation.VERTICAL , true, true, false);

			chart.setBackgroundPaint(Color.WHITE);

			ChartPanel chartPanel = new ChartPanel(chart);

			JFrame f = new JFrame("");
			f.setSize(1000, 500);
			f.getContentPane().add(chartPanel);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);

			// 6. 닫기
			rs.close();
			st.close();
			con.close();

		} catch(Exception e) {
			System.out.println("실패 : " + e.getMessage());
			e.printStackTrace();
		}
	}
}