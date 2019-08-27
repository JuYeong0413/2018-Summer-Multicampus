package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import model.LectureModel;
import model.academy.Lecture;

public class LectureView extends JPanel {
	//	member field
	JLabel num, lectureName, teacher, day, time, room, lRoom, capacity, lCapacity;
	JTextField		tfLectureNum, tfLectureName, tfTeacherName, tfRoomNum, tfCapacity;
	JRadioButton	cbMon, cbTue, cbWed, cbThur, cbFri;
	JRadioButton	rb_1, rb_2, rb_3, rb_4, rb_5;

	JButton			bLectureInsert, bLectureModify, bLectureDelete, bRegisterLecture;

	JComboBox		comLectureSearch;
	JTextField		tfLectureSearch;
	JTable			tableLecture;

	ButtonGroup group, group2;

	Font f;

	LectureTableModel tbModelLecture;

	LectureModel db;


	public LectureView() {
		addLayout(); // 화면설계
		eventProc();
		connectDB(); // DB연결
		initStyle();
	} // end of LectureView()

	void initStyle() {
		tfLectureNum.setEditable(false);
		tfCapacity.setEditable(false);
	} // end of initStyle()

	public void connectDB() {
		try {
			db = new LectureModel();
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // end of ConnectDB()

	public void eventProc() {
		// 추가 버튼이 눌렸을 때
		bLectureInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// 각 화면의 입력값들 얻어오기
				// 그 입력값들을 Lecture 클래스의 멤버로 저장
				// LectureModel의 insertLecture() 호출

				String lectureName = tfLectureName.getText();
				String teacher = tfTeacherName.getText();
				String day = getDay(); 
				String time = getTime();
				String room = tfRoomNum.getText();
				
				if(lectureName == null || teacher == null || day.equals("") || time.equals("") || room == null) {
					JOptionPane.showMessageDialog(null, "모든 정보를 입력해 주세요.");
					return;
				}

				Lecture lec = new Lecture();
				lec.setLectureName(lectureName);
				lec.setTeacher(teacher);
				lec.setDay(day);
				lec.setTime(Integer.parseInt(time));
				lec.setRoom(Integer.parseInt(room));
				lec.setCapacity(0);

				try {
					db.insertLecture(lec);
					// 화면초기화
					tfLectureNum.setText(null);
					tfLectureName.setText(null);
					tfTeacherName.setText(null);
					group.clearSelection();
					group2.clearSelection();
					tfRoomNum.setText(null);
					tfCapacity.setText(null);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});

		// 검색 텍스트필드에서 엔터 이벤트 발생 시
		tfLectureSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchLecture();
			}
		});

		// 테이블에서 클릭 이벤트
		tableLecture.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				int row = tableLecture.getSelectedRow();
				int col = 0;
				String lecNum = (String)tableLecture.getValueAt(row, col);

				try {
					Lecture lec = db.selectByPk(lecNum);
					// 각 화면에 해당 강의정보를 출력
					tfLectureNum.setText(String.valueOf(lec.getLectureNum()));
					tfLectureName.setText(lec.getLectureName());
					tfTeacherName.setText(lec.getTeacher());
					if(lec.getDay().equals("월")) { cbMon.setSelected(true); };
					if(lec.getDay().equals("화")) { cbTue.setSelected(true); };
					if(lec.getDay().equals("수")) { cbWed.setSelected(true); };
					if(lec.getDay().equals("목")) { cbThur.setSelected(true); };
					if(lec.getDay().equals("금")) { cbFri.setSelected(true); };	
					if(lec.getTime() == 1) { rb_1.setSelected(true); }
					else if(lec.getTime() == 2) { rb_2.setSelected(true); }
					else if(lec.getTime() == 3) { rb_3.setSelected(true); }
					else if(lec.getTime() == 4) { rb_4.setSelected(true); }
					else if(lec.getTime() == 5) { rb_5.setSelected(true); };
					tfRoomNum.setText(String.valueOf(lec.getRoom()));
					tfCapacity.setText(String.valueOf(lec.getCapacity()));

				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});

		// 수정 버튼이 눌렸을 때
		bLectureModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// 1. 화면 텍스트필드의 입력값 얻어오기
				String lectureNum = tfLectureNum.getText();
				String lectureName = tfLectureName.getText();
				String teacher = tfTeacherName.getText();
				String day = getDay();
				String time = getTime();
				String room = tfRoomNum.getText();
				String capacity = tfCapacity.getText();
				
				if(lectureNum.equals("")) return;

				// 2. 1값들을 Lecture 클래스의 멤버로지정
				Lecture lec = new Lecture();
				lec.setLectureNum(Integer.parseInt(lectureNum));
				lec.setLectureName(lectureName);
				lec.setTeacher(teacher);
				lec.setDay(day);
				lec.setTime(Integer.parseInt(time));
				lec.setRoom(Integer.parseInt(room));
				lec.setCapacity(Integer.parseInt(capacity));

				// 3. modifyLecture() 메소드 호출하여 2번 lec 객체를 넘김
				try {
					db.modifyLecture(lec);
					// 화면초기화
					tfLectureNum.setText(null);
					tfLectureName.setText(null);
					tfTeacherName.setText(null);
					group.clearSelection();
					group2.clearSelection();
					tfRoomNum.setText(null);
					tfCapacity.setText(null);
					tbModelLecture.fireTableDataChanged();
					searchLecture();
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, "입력실패");
					e.printStackTrace();
				}

			}
		});

		// 삭제 버튼이 눌렸을 때
		bLectureDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String lecNum = tfLectureNum.getText();
				
				if(lecNum.equals("")) return;

				try {
					db.deleteAttendance(lecNum);
					db.deleteManage(lecNum);
					db.deleteLecture(lecNum);
					// 화면초기화
					tfLectureNum.setText(null);
					tfLectureName.setText(null);
					tfTeacherName.setText(null);
					group.clearSelection();
					group2.clearSelection();
					tfRoomNum.setText(null);
					tfCapacity.setText(null);
					tbModelLecture.fireTableDataChanged();
					searchLecture();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});

		// 신청 버튼
		bRegisterLecture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String lecNum = tfLectureNum.getText();
				String day = getDay(); 
				String time = getTime();
				if(lecNum.length() == 0) return; // 강의정보가 띄워저 있지 않은 경우

				try {
					String tel = enterTel(); // 학생 연락처 입력
					if(tel == null) return; // 취소 버튼을 누른 경우
					if(tel.length() == 0) { // 전화번호 입력을 안 했는데 확인을 누른 경우
						System.out.println("전화번호 입력 안됨");
						return;
					}
					else {
						if(db.searchTel(tel) == false) { // 전화번호 정보가 없는 경우
							noData();
							return;
						}
						else { // 전화번호 정보가 있는 경우
							// 정원 초과인지 확인
							if(db.checkCapacity(lecNum) == 30) {
								capacityFull();
								return;
							}
							// 중복된 강의인지 확인 (강의번호로 확인)
							if(db.checkLecture(tel, lecNum) == false) {
								sameLecture();
								return;
							}
							// 요일과 시간이 겹치는지 확인
							ArrayList studentData = db.getLecture(tel);
							ArrayList lectureData = db.getTime(lecNum, day, time);
							if(checkTime(studentData, lectureData) == false) {
								sameTime();
								return;
							}
							
							db.registerLecture(lecNum, tel);
							db.increaseCapacity(lecNum);
							complete();
							db.register(lecNum, tel);
							tfCapacity.setText(Integer.toString(db.checkCapacity(lecNum)));
						}
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});

	} // end of eventProc()

	public String enterTel() {		
		String tel = (String) JOptionPane.showInputDialog(null,
				"수강할 학생의 연락처를 입력해 주세요.",
				"수강생 정보 등록",
				JOptionPane.PLAIN_MESSAGE);
		
		return tel;
	}

	public void noData() {
		JOptionPane.showMessageDialog(null,
				"학생 정보가 없습니다.",
				"error",
				JOptionPane.ERROR_MESSAGE);
	} // end of noData()

	public void complete() {
		JOptionPane.showMessageDialog(null,
				"신청 완료!",
				"",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void capacityFull() {
		JOptionPane.showMessageDialog(null,
				"강의정원 초과!\n신청이 불가능합니다.",
				"",
				JOptionPane.WARNING_MESSAGE);
	}
	
	public void sameLecture() {
		JOptionPane.showMessageDialog(null,
				"중복된 강의입니다!",
				"",
				JOptionPane.WARNING_MESSAGE);
	}
	
	public void sameTime() {
		JOptionPane.showMessageDialog(null,
				"해당 강의 시간에\n수업이 존재합니다.",
				"",
				JOptionPane.WARNING_MESSAGE);
	}

	public boolean checkTime(ArrayList studentData, ArrayList lectureData) {
		boolean check = true;
		
		for(int i = 0; i < studentData.size(); i++) {
			if(studentData.get(i).equals(lectureData.get(0))) check = false;
		}
		
		return check;
	}
	
	public void searchLecture() {
		int sel = comLectureSearch.getSelectedIndex();
		String word = tfLectureSearch.getText();
		try {
			ArrayList data = db.searchLecture(sel, word);
			tbModelLecture.data = data;
			tbModelLecture.fireTableDataChanged();
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // end of searchLectrue()

	public String getDay() {
		String day = "";
		if(cbMon.isSelected()) { day = "월"; }
		else if(cbTue.isSelected()) { day = "화"; }
		else if(cbWed.isSelected()) { day = "수"; }
		else if(cbThur.isSelected()) { day = "목"; }
		else if(cbFri.isSelected()) { day ="금"; };

		return day;
	}

	public String getTime() {
		String time = "";

		if(rb_1.isSelected()) { time = "1"; }
		else if(rb_2.isSelected()) { time = "2"; }
		else if(rb_3.isSelected()) { time = "3"; }
		else if(rb_4.isSelected()) { time = "4"; }
		else if(rb_5.isSelected()) { time = "5"; };

		return time;
	}

	// 화면설계 메소드
	public void addLayout() {
		setLayout(null);
		setBounds(0, 0, 1000, 800);

		tfLectureNum  = new JTextField();
		tfLectureName = new JTextField();
		tfTeacherName = new JTextField();
		cbMon = new JRadioButton("월");
		cbTue = new JRadioButton("화");
		cbWed = new JRadioButton("수");
		cbThur = new JRadioButton("목");
		cbFri = new JRadioButton("금");
		rb_1 = new JRadioButton("1");
		rb_2 = new JRadioButton("2");
		rb_3 = new JRadioButton("3");
		rb_4 = new JRadioButton("4");
		rb_5 = new JRadioButton("5");
		tfRoomNum  = new JTextField();
		tfCapacity = new JTextField();

		group = new ButtonGroup();
		group.add(cbMon);
		group.add(cbTue);
		group.add(cbWed);
		group.add(cbThur);
		group.add(cbFri);
		
		group2 = new ButtonGroup();
		group2.add(rb_1);
		group2.add(rb_2);
		group2.add(rb_3);
		group2.add(rb_4);
		group2.add(rb_5);

		String []cbLectureSearch = {"강의명", "강사명", "강의실"};
		comLectureSearch = new JComboBox(cbLectureSearch);
		tfLectureSearch  = new JTextField(15);

		bLectureInsert   = new JButton("추가");
		bLectureModify   = new JButton("수정");
		bLectureDelete   = new JButton("삭제");
		bRegisterLecture = new JButton("신청");

		tbModelLecture = new LectureTableModel();
		tableLecture = new JTable(tbModelLecture);

		DefaultTableCellRenderer cellAlignCenter = new DefaultTableCellRenderer();
		cellAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		TableColumnModel tcm = tableLecture.getColumnModel();
		for(int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(cellAlignCenter);
		}

		// 화면구성
		// 왼쪽영역
		JPanel p_west = new JPanel();	
		p_west.setLayout(null);
		TitledBorder oneTb = new TitledBorder("강의정보");		
		p_west.setBorder(oneTb);

		num = new JLabel("강의번호");
		lectureName = new JLabel("강  의  명");
		teacher = new JLabel("강  사  명");
		day = new JLabel("강의요일");
		time = new JLabel("강의시간");
		room = new JLabel("강  의  실");
		lRoom = new JLabel("호");
		capacity = new JLabel("인        원");
		lCapacity = new JLabel(" /  3 0 명");

		f = new Font("Dialog", Font.BOLD, 16);
		num.setFont(f);
		lectureName.setFont(f);
		teacher.setFont(f);
		day.setFont(f);
		time.setFont(f);
		room.setFont(f);
		lRoom.setFont(f);
		capacity.setFont(f);
		lCapacity.setFont(f);

		cbMon.setFont(f);
		cbTue.setFont(f);
		cbWed.setFont(f);
		cbThur.setFont(f);
		cbFri.setFont(f);
		rb_1.setFont(f);
		rb_2.setFont(f);
		rb_3.setFont(f);
		rb_4.setFont(f);
		rb_5.setFont(f);

		num.setBounds(25, 20, 80, 50);
		lectureName.setBounds(25, 70, 80, 50);
		teacher.setBounds(25, 120, 80, 50);
		day.setBounds(25, 170, 80, 50);
		time.setBounds(25, 220, 80, 50);
		room.setBounds(25, 270, 80, 50);
		lRoom.setBounds(380, 270, 80, 50);
		capacity.setBounds(25, 320, 80, 50);
		lCapacity.setBounds(170, 320, 80, 50);

		tfLectureNum.setBounds(110, 30, 300, 30);
		tfLectureName.setBounds(110, 80, 300, 30);
		tfTeacherName.setBounds(110, 130, 300, 30);
		cbMon.setBounds(110, 180, 50, 30);
		cbTue.setBounds(170, 180, 50, 30);
		cbWed.setBounds(230, 180, 50, 30);
		cbThur.setBounds(300, 180, 50, 30);
		cbFri.setBounds(360, 180, 50, 30);
		rb_1.setBounds(110, 230, 50, 30);
		rb_2.setBounds(170, 230, 50, 30);
		rb_3.setBounds(230, 230, 50, 30);
		rb_4.setBounds(300, 230, 50, 30);
		rb_5.setBounds(360, 230, 50, 30);
		tfRoomNum.setBounds(110, 280, 265, 30);
		tfCapacity.setBounds(110, 330, 50, 30);

		p_west.add(num);
		p_west.add(lectureName);
		p_west.add(teacher);
		p_west.add(day);
		p_west.add(time);
		p_west.add(room);
		p_west.add(lRoom);
		p_west.add(capacity);
		p_west.add(lCapacity);

		p_west.add(tfLectureNum);
		p_west.add(tfLectureName);
		p_west.add(tfTeacherName);
		p_west.add(cbMon);
		p_west.add(cbTue);
		p_west.add(cbWed);
		p_west.add(cbThur);
		p_west.add(cbFri);
		p_west.add(rb_1);
		p_west.add(rb_2);
		p_west.add(rb_3);
		p_west.add(rb_4);
		p_west.add(rb_5);
		p_west.add(tfRoomNum);
		p_west.add(tfCapacity);

		bLectureInsert.setFont(f);
		bLectureModify.setFont(f);
		bLectureDelete.setFont(f);
		bRegisterLecture.setFont(f);

		bLectureInsert.setBounds(30, 400, 80, 50);
		bLectureModify.setBounds(130, 400, 80, 50);
		bLectureDelete.setBounds(230, 400, 80, 50);
		bRegisterLecture.setBounds(330, 400, 80, 50);

		p_west.add(bLectureInsert);
		p_west.add(bLectureModify);
		p_west.add(bLectureDelete);
		p_west.add(bRegisterLecture);

		// 화면구성 - 오른쪽영역
		JPanel p_east = new JPanel();
		p_east.setLayout(new BorderLayout());

		JPanel p_east_north = new JPanel();
		p_east_north.add(comLectureSearch);
		p_east_north.add(tfLectureSearch);
		p_east_north.setBorder(new TitledBorder("강의검색"));

		p_east.add(p_east_north, BorderLayout.NORTH);
		p_east.add(new JScrollPane(tableLecture), BorderLayout.CENTER);

		// 전체 화면에 왼쪽 오른쪽 붙이기
		setLayout(new GridLayout(1,2));

		add(p_west);
		add(p_east);
	}

	//화면에 테이블 붙이는 메소드 
	class LectureTableModel extends AbstractTableModel { 

		ArrayList data = new ArrayList();
		String [] columnNames = {"강의번호", "강의명", "강사명", "요일", "교시", "강의실"};

		// 1. 기본적인 TabelModel  만들기
		public int getColumnCount() { 
			return columnNames.length; 
		} 

		public int getRowCount() { 
			return data.size(); 
		} 

		public Object getValueAt(int row, int col) { 
			ArrayList temp = (ArrayList)data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col){
			return columnNames[col];
		}
	}
}
