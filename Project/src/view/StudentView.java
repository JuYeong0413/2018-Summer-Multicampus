package view;

import java.awt.*;
import javax.swing.*;

import model.StudentModel;
import model.academy.Student;

import java.awt.event.*;
import java.util.ArrayList;


public class StudentView extends JPanel {
	JLabel lName, lTel, lBday, lGender, lAddr, lEmail, lNameSearch, lTelSearch;
	JTextField tfStudentName, tfStudentTel, tfStudentBday, tfStudentGender, tfStudentAddr, tfStudentEmail;
	JButton bStudentRegist, bStudentModify, bStudentDelete;

	JTextField tfStudentNameSearch, tfStudentTelSearch;
	JButton bStudentNameSearch, bStudentTelSearch;

	StudentModel db;
	
	JLabel[] telLabel;
	JRadioButton[] telButton;
	
	Font f;

	Object[] selectTel;

	public StudentView() {
		addLayout();
		connectDB();
		eventProc();
	} // end of StudentView()

	public void eventProc() {
		ButtonEventHandler btnHandler = new ButtonEventHandler();

		// 이벤트 등록
		bStudentRegist.addActionListener(btnHandler);
		bStudentModify.addActionListener(btnHandler);
		bStudentDelete.addActionListener(btnHandler);
		bStudentNameSearch.addActionListener(btnHandler);
		bStudentTelSearch.addActionListener(btnHandler);
	} // end of eventProc()

	// button event handler
	class ButtonEventHandler implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Object o = ev.getSource();

			if(o == bStudentRegist) {
				registStudent(); // register student
			}
			else if(o == bStudentModify) {
				updateStudent(); // modify student info
			}
			else if(o == bStudentDelete) {
				deleteStudent();
			}
			else if(o == bStudentTelSearch) {
				searchByTel(); // student search by tel
			}
			else if(o == bStudentNameSearch) {
				searchByName();
			}
		}
	} // end of ButtonEventHandler class

	public void registStudent() {
		// 1. 화면 텍스트필드의 입력값 얻어오기
		String name = tfStudentName.getText();
		String tel = tfStudentTel.getText();
		String bday = tfStudentBday.getText();
		String gender = tfStudentGender.getText();
		String addr = tfStudentAddr.getText();
		String email = tfStudentEmail.getText();

		// 2. 위의 값들을 Student 클래스의 멤버로 지정
		Student s = new Student();
		s.setStudentName(name);
		s.setStudentTel(tel);
		s.setStudentBday(bday);
		s.setStudentGender(gender);
		s.setStudentAddr(addr);
		s.setStudentEmail(email);

		// 3. Model 클래스 안에 insertStudent() method 호출하여 2번 academy 객체를 넘김
		try {
			db.insertStudent(s);
			// 화면 초기화
			tfStudentName.setText(null);
			tfStudentTel.setText(null);
			tfStudentBday.setText(null);
			tfStudentGender.setText(null);
			tfStudentAddr.setText(null);
			tfStudentEmail.setText(null);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "입력실패");
			e.printStackTrace();
		}
	} // end of registStudent()

	public void searchByTel() {
		// 1. 입력한 전화번호 얻어오기
		// 2. Model의 전화번호 검색메소드 selectByTel() 호출
		// 3. 2번의 넘겨받은 Student의 각각의 값을 화면 텍스트 필드 지정
		String tel = tfStudentTelSearch.getText();
		if(tel.length() == 0) {
			JOptionPane.showMessageDialog(null, "연락처를 입력하세요.");
			return;
		}
		
		try {
			Student s = db.selectByTel(tel);

			if(s == null) {
				noData();
			} else {
				tfStudentName.setText(s.getStudentName());
				tfStudentTel.setText(s.getStudentTel());
				tfStudentBday.setText(s.getStudentBday());
				tfStudentGender.setText(s.getStudentGender());
				tfStudentAddr.setText(s.getStudentAddr());
				tfStudentEmail.setText(s.getStudentEmail());
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "검색실패");
			e.printStackTrace();
		}
	} // end of searchByTel()

	public void searchByName() {
		// 1. 입력한 이름 얻어오기
		// 2. Model의 이름 검색메소드 selectByName() 호출
		// 3. 해당 이름을 가진 학생이 한 명이라면 학생 정보를 화면 텍스트 필드에 출력
		// 4. 동명이인이 있다면 dialog 띄워서 선택
		String name = tfStudentNameSearch.getText();
		if(name.length() == 0) {
			JOptionPane.showMessageDialog(null, "이름을 입력하세요.");
			return;
		}
		try {
			ArrayList data = db.selectByName(name);

			if(data.isEmpty()) {
				JOptionPane.showMessageDialog(null, "등록된 이름이 없습니다.");
			} else if(data.size() == 1) {
				String tel = data.get(0).toString();
				tel = tel.substring(1, 14);

				Student s = db.selectByTel(tel);

				tfStudentName.setText(s.getStudentName());
				tfStudentTel.setText(s.getStudentTel());
				tfStudentBday.setText(s.getStudentBday());
				tfStudentGender.setText(s.getStudentGender());
				tfStudentAddr.setText(s.getStudentAddr());
				tfStudentEmail.setText(s.getStudentEmail());
			} else { // 동명이인 선택					
				Object[] bDialog = {"확인", "취소"};
				
				ButtonGroup group = new ButtonGroup();
				
				//data.sort();
				
				for(int i = 0; i < data.size(); i++) {
					if(i == 0) {
						telLabel = new JLabel[data.size()];
						telButton = new JRadioButton[data.size()];
						selectTel = new Object[data.size()];
					}
					telButton[i] = new JRadioButton();
					String str = data.get(i).toString();
					str = str.substring(1, 14);
					selectTel[i] = str;
					telLabel[i] = new JLabel(str);
					group.add(telButton[i]);
				}
				
				String selected = (String) JOptionPane.showInputDialog(this, "전화번호를 선택해 주세요.",
											name + " 동명이인 선택",
											JOptionPane.INFORMATION_MESSAGE,
											null,     //do not use a custom Icon
											selectTel,  //the titles of buttons
											selectTel[0]);
				
				//If a string was returned
				if ((selected != null) && (selected.length() > 0)) {
				    Student s = db.selectByTel(selected);
				    
				    tfStudentName.setText(s.getStudentName());
				    tfStudentTel.setText(s.getStudentTel());
				    tfStudentBday.setText(s.getStudentBday());
				    tfStudentGender.setText(s.getStudentGender());
				    tfStudentAddr.setText(s.getStudentAddr());
				    tfStudentEmail.setText(s.getStudentEmail());
				}			
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "검색실패");
			e.printStackTrace();
		}
	} // end of searchByName()
	
	public void noData() {
		JOptionPane.showMessageDialog(null,
				"학생 정보가 없습니다.",
				"error",
				JOptionPane.ERROR_MESSAGE);
	} // end of noData()

	public void updateStudent() {
		// 1. 화면 텍스트필드의 입력값 얻어오기
		String name = tfStudentName.getText();
		String tel = tfStudentTel.getText();
		String bday = tfStudentBday.getText();
		String gender = tfStudentGender.getText();
		String addr = tfStudentAddr.getText();
		String email = tfStudentEmail.getText();

		// 2. 위의 값들을 Student 클래스의 멤버로지정
		Student s = new Student();
		s.setStudentName(name);
		s.setStudentTel(tel);
		s.setStudentBday(bday);
		s.setStudentGender(gender);
		s.setStudentAddr(addr);
		s.setStudentEmail(email);

		// 3. Model 클래스 안에 updateStudent() 메소드 호출하여 2번 academy 객체를 넘김
		try {
			db.updateStudent(s);
			// 화면 초기화
			tfStudentName.setText(null);
			tfStudentTel.setText(null);
			tfStudentBday.setText(null);
			tfStudentGender.setText(null);
			tfStudentAddr.setText(null);
			tfStudentEmail.setText(null);
			tfStudentNameSearch.setText(null);
			tfStudentTelSearch.setText(null);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "입력실패");
			e.printStackTrace();
		}
	} // end of updateStudent()

	public void deleteStudent() {
		String tel = tfStudentTel.getText();

		try {
			db.deleteStudent(tel);
			// 화면 초기화
			tfStudentName.setText(null);
			tfStudentTel.setText(null);
			tfStudentBday.setText(null);
			tfStudentGender.setText(null);
			tfStudentAddr.setText(null);
			tfStudentEmail.setText(null);
			tfStudentNameSearch.setText(null);
			tfStudentTelSearch.setText(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of deleteStudent();

	public void connectDB() {
		try {
			db = new StudentModel();
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // end of ConnectDB()

	public void addLayout() {
		setLayout(null);
		setBounds(0, 0, 1000, 800);

		tfStudentName 		= new JTextField();
		tfStudentTel 		= new JTextField();
		tfStudentBday 		= new JTextField();
		tfStudentGender 	= new JTextField();
		tfStudentAddr 		= new JTextField();
		tfStudentEmail 		= new JTextField();

		bStudentRegist 		= new JButton("등록");
		bStudentModify 		= new JButton("수정");
		bStudentDelete	    = new JButton("삭제");

		tfStudentNameSearch = new JTextField();
		tfStudentTelSearch  = new JTextField();

		bStudentNameSearch  = new JButton("이름검색");
		bStudentTelSearch   = new JButton("번호검색");

		//////////////////////////////////////////////////////////////////////

		f = new Font("Dialog", Font.BOLD, 16);

		lName 		= new JLabel("이   름");
		lTel 		= new JLabel("연락처");
		lBday 		= new JLabel("생년월일");
		lGender 	= new JLabel("성       별");
		lAddr 		= new JLabel("주   소");
		lEmail 	 	= new JLabel("이메일");
		lNameSearch = new JLabel("이   름");
		lTelSearch  = new JLabel("연락처");

		lName.setFont(f);
		lTel.setFont(f);
		lBday.setFont(f);
		lGender.setFont(f);
		lAddr.setFont(f);
		lEmail.setFont(f);
		lNameSearch.setFont(f);
		lTelSearch.setFont(f);

		lName.setBounds(20, 20, 70, 30);
		lBday.setBounds(440, 20, 100, 30);
		lTel.setBounds(20, 80, 80, 30);
		lGender.setBounds(440, 80, 100, 30);
		lAddr.setBounds(20, 140, 100, 30);
		lEmail.setBounds(20, 200, 100, 30);
		lNameSearch.setBounds(20, 360, 80, 30);
		lTelSearch.setBounds(20, 420, 80, 30);

		add(lName);
		add(lTel);
		add(lBday);
		add(lGender);
		add(lAddr);
		add(lEmail);
		add(lNameSearch);
		add(lTelSearch);

		//////////////////////////////////////////////////////////////////////

		tfStudentName.setBounds(110, 22, 300, 30);
		tfStudentBday.setBounds(550, 22, 300, 30);
		tfStudentTel.setBounds(110, 82, 300, 30);
		tfStudentGender.setBounds(550, 82, 300, 30);
		tfStudentAddr.setBounds(110, 142, 740, 30);
		tfStudentEmail.setBounds(110, 202, 740, 30);

		bStudentRegist.setBounds(130, 270, 100, 50);
		bStudentModify.setBounds(380, 270, 100, 50);
		bStudentDelete.setBounds(620, 270, 100, 50);

		tfStudentNameSearch.setBounds(110, 363, 600, 30);
		tfStudentTelSearch.setBounds(110, 423, 600, 30);

		bStudentNameSearch.setBounds(730, 358, 120, 40);
		bStudentTelSearch.setBounds(730, 418, 120, 40);

		bStudentRegist.setFont(f);
		bStudentModify.setFont(f);
		bStudentDelete.setFont(f);

		bStudentNameSearch.setFont(f);
		bStudentTelSearch.setFont(f);

		add(tfStudentName);
		add(tfStudentTel);
		add(tfStudentBday);
		add(tfStudentGender);
		add(tfStudentAddr);
		add(tfStudentEmail);

		add(bStudentRegist);
		add(bStudentModify);
		add(bStudentDelete);

		add(tfStudentNameSearch);
		add(tfStudentTelSearch);

		add(bStudentNameSearch);
		add(bStudentTelSearch);
	}
}
