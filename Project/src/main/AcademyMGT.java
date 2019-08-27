package main;

import java.awt.*;
import javax.swing.*;

import view.StudentView;
import view.LectureView;
import view.StudentMGTView;
import view.AttendanceView;
import view.StatsView;

public class AcademyMGT extends JFrame {
	
	StudentView student;
	LectureView lecture;
	StudentMGTView studentMGT;
	AttendanceView attendance;
	StatsView stats;
	
	public AcademyMGT() {
		// 각각의 화면을 관리하는 클래스 객체 생성
		student = new StudentView();
		lecture = new LectureView();
		studentMGT = new StudentMGTView();
		attendance = new AttendanceView();
		stats = new StatsView();
		
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("학생관리", student);
		pane.addTab("강의관리/신청", lecture);
		pane.addTab("수강생조회", studentMGT);
		pane.addTab("출결관리", attendance);
		pane.addTab("통계", stats);
		
		pane.setSelectedIndex(2);
		setTitle("");
		
		
		// 화면크기지정
		add("Center", pane);
		setSize(900, 550);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new AcademyMGT();
	}

}
