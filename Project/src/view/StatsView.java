package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import model.StatsModel;

public class StatsView extends JPanel {
	JButton bStudentGender, bNewStudent, bLecture, bTL, bTS;
	
	Font f;
	
	StatsModel db;

	public StatsView() {
		addLayout();
		connectDB();
		eventProc();
	}
	
	public void eventProc() {
		bStudentGender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.getStudentGender();
			}
		});
		
		bNewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.getNewStudent();
			}
		});
		
		bLecture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.getLecture();
			}
		});
		
		bTL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.getTL();
			}
		});
		
		bTS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.getTS();
			}
		});
	} // end of eventProc()
	
	public void connectDB() {
		try {
			db = new StatsModel();
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // end of connectDB()
	
	public void addLayout() {
		setLayout(null);
		setBounds(0, 0, 900, 550);
		
		bStudentGender = new JButton("남녀 학생 수 통계");
		bNewStudent = new JButton("월별 신규등록 학생 수 추이");
		bLecture = new JButton("수업별 수강생 수 통계");
		bTL = new JButton("강사별 담당 강의 개수 통계");
		bTS = new JButton("강사별 수강생 수 통계");

		f = new Font("Dialog", Font.BOLD, 16);

		bStudentGender.setFont(f);
		bNewStudent.setFont(f);
		bLecture.setFont(f);
		bTL.setFont(f);
		bTS.setFont(f);
		
		bStudentGender.setBounds(300, 70, 280, 40);
		bNewStudent.setBounds(300, 140, 280, 40);
		bLecture.setBounds(300, 210, 280, 40);
		bTL.setBounds(300, 280, 280, 40);
		bTS.setBounds(300, 350, 280, 40);
		
		add(bStudentGender);
		add(bNewStudent);
		add(bLecture);
		add(bTL);
		add(bTS);
		
		//////////////////////////////////////////////////////////////////////
		
		
	}
}
