package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import model.AttendanceModel;
import model.academy.Student;

public class AttendanceView extends JPanel {
	// member field
	JTextField tfLectureNum, tfLectureName, tfTeacherName;
	JButton bSearch, bSave, bLoad;
	//JRadioButton bAttendance;
	JLabel attend, late, absent, now, total;

	JLabel session, status_1, status_2, status_3, status_4, status_5, status_6;

	JSpinner spinner;
	SpinnerModel model;

	JTable lectureList, studentList;

	LectureTableModel lectureTM;
	//AttendanceTableModel attendanceTM;
	studentModel studentTM;

	DefaultTableCellRenderer cellAlignCenter;

	AttendanceModel db;

	String num, name, teacher;
	String lecNum;
	int lNum;

	public AttendanceView() {
		addLayout();
		connectDB();
		eventProc();
	} // end of AttendanceView()

	void eventProc() {
		// 조회 버튼 눌렀을 때
		bSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num = tfLectureNum.getText();
				name = tfLectureName.getText();
				teacher = tfTeacherName.getText();

				if(!num.isEmpty()) {
					if(name.isEmpty() && teacher.isEmpty()) {
						// 1. 강의번호만으로 검색
						searchByNum();
					}
					else if(!name.isEmpty() && teacher.isEmpty()) {
						// 2. 강의번호 + 강의명으로 검색
						searchByNumName();
					}
					else if(name.isEmpty() && !teacher.isEmpty()) {
						// 3. 강의번호 + 강사명으로 검색
						searchByNumTeacher();
					}
					else if(!name.isEmpty() && !teacher.isEmpty()) {
						// 4. 강의번호 + 강의명 + 강사명으로 검색
						searchByAll();
					}
				}
				else if(num.isEmpty()) {
					if(name.isEmpty() && teacher.isEmpty()) {
						// 1. 강의명만으로 검색
						searchByLecture();
					}
					else if(name.isEmpty() && !teacher.isEmpty()) {
						// 2. 강사명만으로 검색
						searchByTeacher();
					}
					else {
						// 3. 강의명 + 강사명으로 검색
						searchByNameTeacher();
					}
				}
			}
		});

		// 저장 버튼 눌렀을 때
		bSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					//spinner.commitEdit();
					int classNum = Integer.parseInt(model.getValue().toString());
					//System.out.println(classNum);

					// attendance 업데이트 TODO
					saveAttendance(lNum, classNum);
					// 화면에 출결상태 출력
					//attend.setText(String.valueOf(getAttend()) + "명");
					//late.setText(String.valueOf(getLate()) + "명");
					//absent.setText(String.valueOf(getAbsent()) + "명");
					//now.setText(String.valueOf(getAttend()) + "명");
					//total.setText(String.valueOf(getTotal()) + "명");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// 테이블에서 클릭 이벤트
		lectureList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				int row = lectureList.getSelectedRow();
				int col = 0;
				lecNum = (String) lectureList.getValueAt(row, col);
				try {
					loadList(lecNum);
				} catch(Exception e) {
					e.printStackTrace();
				}
				lecNum = lecNum.trim();
				lNum = Integer.parseInt(lecNum);
				
				try {
					spinner.commitEdit();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				int classNum = Integer.parseInt(model.getValue().toString());

				// attendance 업데이트 TODO
				saveAttendance(lNum, classNum);
				// 화면에 출결상태 출력
				attend.setText(String.valueOf(getAttend()) + "명");
				late.setText(String.valueOf(getLate()) + "명");
				absent.setText(String.valueOf(getAbsent()) + "명");
				now.setText(String.valueOf(getAttend()+getLate()) + "명");
				total.setText(String.valueOf(getTotal()) + "명");
			}
		});

		// 불러오기 버튼 눌렀을 때
		bLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
	} // end of eventProc()


	public void saveAttendance(int lNum, int classNum) {
		try {
			//db.updateAttendance(lNum, classNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadList(String lecNum) { // TODO
		try {
			ArrayList result = db.getList(lecNum);

			if(result.isEmpty()) {
				JOptionPane.showMessageDialog(null, "수강중인 학생이 없습니다.");
			} else {
				studentList.setModel(studentTM);
				//studentTM.addColumn("출석");
				/*
				Object[][] list = null;

				for(int i = 0; i < result.size(); i++) {
					String str = result.get(i).toString();
					str = str.substring(1, str.length() - 1);
					String[] data = str.split(",");
					System.out.println(data[0]);
					data[1] = data[1].trim();
					//data[2] = data[1].trim();
					//data[3] = data[1].trim();
					//data[4] = data[1].trim();
					
					list[0][0] = data[0];
					list[1][1] = data[1];
					list[2][2] = new Boolean(false);
					list[3][3] = new Boolean(false);
					list[4][4] = new Boolean(true);
					
					
					for(int j = 0; j < 5; j++) {						
						list[i][j] = data[j];
						if(j >= 2) list[i][j] = new Boolean(false);
					}
				}
				
				for(Object o : list) System.out.println(list);
				
				Object[] s = result.toArray(new Object[result.size()]);
				*/
				studentTM.data = result;

				TableColumnModel tcm2 = studentList.getColumnModel();
				for(int i = 0; i < tcm2.getColumnCount(); i++) {
					tcm2.getColumn(i).setCellRenderer(cellAlignCenter);
				}

				studentTM.fireTableDataChanged();

			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "검색실패");
			e.printStackTrace();
		}
	} // end of loadList()

	public int getAttend() {
		int attend = 0;
		try {
			attend = db.getAttend(lNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attend;
	} // end of getAttend()

	public int getLate() {
		int late = 0;
		try {
			late = db.getLate(lNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return late;
	} // end of getLate()

	public int getAbsent() {
		int absent = 0;
		try {
			absent = db.getAbsent(lNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return absent;
	} // end of getAbsent()

	public int getTotal() {
		int total = 0;
		try {
			total = db.getTotal(lNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	} // end of getTotal()

	public void searchByNumName() {
		try {
			ArrayList result = db.selectByNumName(num, name);

			if(result.isEmpty()) {
				JOptionPane.showMessageDialog(null, "강의 검색 실패");
			} else { // 강의 목록 불러오기				
				lectureTM.data = result;
				lectureTM.fireTableDataChanged();
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "검색실패");
			e.printStackTrace();
		}
	} // end of searchByNumName()

	public void searchByNumTeacher() {
		try {
			ArrayList result = db.selectByNumTeacher(num, teacher);

			if(result.isEmpty()) {
				JOptionPane.showMessageDialog(null, "강의 검색 실패");
			} else { // 강의 목록 불러오기				
				lectureTM.data = result;
				lectureTM.fireTableDataChanged();
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "검색실패");
			e.printStackTrace();
		}
	} // end of searchByNumTeacher()

	public void searchByAll() {
		try {
			ArrayList result = db.selectByAll(num, name, teacher);

			if(result.isEmpty()) {
				JOptionPane.showMessageDialog(null, "강의 검색 실패");
			} else { // 강의 목록 불러오기				
				lectureTM.data = result;
				lectureTM.fireTableDataChanged();
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "검색실패");
			e.printStackTrace();
		}
	} // end of searchByAll()

	public void searchByTeacher() {
		try {
			ArrayList result = db.searchByTeacher(teacher);

			if(result.isEmpty()) {
				JOptionPane.showMessageDialog(null, "등록된 강의가 없습니다.");
			}
			else { // 강의 목록 불러오기					
				lectureTM.data = result;
				lectureTM.fireTableDataChanged();
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "검색실패");
			e.printStackTrace();
		}
	} // end of searchByTeacher()

	public void searchByLecture() {
		try {
			ArrayList result = db.searchByLecture(name);

			if(result.isEmpty()) {
				JOptionPane.showMessageDialog(null, "등록된 강의가 없습니다.");
			}
			else { // 강의 목록 불러오기					
				lectureTM.data = result;
				lectureTM.fireTableDataChanged();
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "검색실패");
			e.printStackTrace();
		}
	} // end of searchByLecture()

	public void searchByNameTeacher() {
		try {
			ArrayList result = db.selectByNameTeacher(name, teacher);

			if(result.isEmpty()) {
				JOptionPane.showMessageDialog(null, "강의 검색 실패");
			} else { // 강의 목록 불러오기				
				lectureTM.data = result;
				lectureTM.fireTableDataChanged();
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "검색실패");
			e.printStackTrace();
		}
	} // end of searchByNumTeacher()

	public void searchByNum() {
		try {
			ArrayList data = db.searchByNum(num);
			lectureTM.data = data;
			lectureTM.fireTableDataChanged();
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // end of searchLecture()

	public void connectDB() {
		try {
			db = new AttendanceModel();
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // end of connectDB()

	public void addLayout() {
		tfLectureNum  = new JTextField(20);
		tfLectureName = new JTextField(20);
		tfTeacherName = new JTextField(20);
		bSearch 	  = new JButton("조회");

		lectureTM  	  = new LectureTableModel();
		lectureList   = new JTable(lectureTM);
		//attendanceTM  = new AttendanceTableModel();
		//studentList   = new JTable(attendanceTM);
		studentTM = new studentModel();
		studentList = new JTable();

		attend 		  = new JLabel();
		late 		  = new JLabel();
		absent		  = new JLabel();
		now			  = new JLabel();
		total 		  = new JLabel();

		model = new SpinnerNumberModel(1, 1, 99, 1);
		spinner = new JSpinner();
		spinner.setModel(model);

		// 화면붙이기
		setLayout(null);
		setBounds(0, 0, 900, 550);

		session = new JLabel("수업차시  ");
		status_1 = new JLabel("출석 :");
		status_2 = new JLabel("지각 :");
		status_3 = new JLabel("결석 :");
		status_4 = new JLabel("(");
		status_5 = new JLabel("/");
		status_6 = new JLabel(")");
		bSave = new JButton("저장");
		bLoad = new JButton("불러오기");

		session.setBounds(10, 450, 80, 40);
		spinner.setBounds(75, 460, 50, 20);
		status_1.setBounds(500, 455, 40, 30);
		attend.setBounds(545, 455, 40, 30);
		status_2.setBounds(590, 455, 40, 30);
		late.setBounds(635, 455, 40, 30);
		status_3.setBounds(680, 455, 40, 30);
		absent.setBounds(725, 455, 40, 30);
		status_4.setBounds(765, 455, 20, 30);
		now.setBounds(781, 455, 40, 30);
		status_5.setBounds(820, 455, 20, 30);
		total.setBounds(835, 455, 40, 30);
		status_6.setBounds(870, 455, 20, 30);
		bSave.setBounds(135, 458, 80, 23);
		bLoad.setBounds(220, 458, 90, 23);

		add(session);
		add(spinner);
		add(status_1);
		add(status_2);
		add(status_3);
		add(status_4);
		add(status_5);
		add(status_6);
		add(bSave);
		//add(bLoad);

		add(attend);
		add(late);
		add(absent);
		add(now);
		add(total);

		// 위쪽 영역
		JPanel pNorth = new JPanel();
		pNorth.setLayout(new GridLayout(0,2));
		pNorth.setBounds(0, 0, 900, 170);

		// 오른쪽 영역
		JPanel pNorthRight = new JPanel();
		JScrollPane lecList = new JScrollPane(lectureList);
		lecList.setPreferredSize(new Dimension(400,165));
		pNorthRight.add(lecList);

		// 왼쪽 영역
		JPanel pNorthLeft = new JPanel();
		pNorthLeft.setLayout(null);
		TitledBorder oneTb = new TitledBorder("강의검색");		
		pNorthLeft.setBorder(oneTb);
		pNorthLeft.setBounds(0, 0, 100, 100);

		JLabel num = new JLabel("강의번호");
		JLabel lectureName = new JLabel("강  의  명");
		JLabel teacher = new JLabel("강  사  명");

		Font f = new Font("Dialog", Font.BOLD, 16);
		num.setFont(f);
		lectureName.setFont(f);
		teacher.setFont(f);
		bSearch.setFont(f);

		num.setBounds(17, 15, 80, 30);
		lectureName.setBounds(17, 50, 80, 30);
		teacher.setBounds(17, 85, 70, 30);

		tfLectureNum.setBounds(100, 15, 320, 30);
		tfLectureName.setBounds(100, 50, 320, 30);
		tfTeacherName.setBounds(100, 85, 320, 30);

		bSearch.setBounds(17, 122, 402, 33);

		pNorthLeft.add(num);
		pNorthLeft.add(tfLectureNum);
		pNorthLeft.add(lectureName);
		pNorthLeft.add(tfLectureName);
		pNorthLeft.add(teacher);
		pNorthLeft.add(tfTeacherName);
		pNorthLeft.add(bSearch);

		pNorth.add(pNorthLeft);
		pNorth.add(pNorthRight);

		add(pNorth);

		cellAlignCenter = new DefaultTableCellRenderer();
		cellAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		TableColumnModel tcm = lectureList.getColumnModel();
		for(int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(cellAlignCenter);
		}

		TableColumnModel tcm2 = studentList.getColumnModel();
		for(int i = 0; i < tcm2.getColumnCount(); i++) {
			tcm2.getColumn(i).setCellRenderer(cellAlignCenter);
		}

		//////////////////////////////////////////////////////////////////////

		JPanel pCenter = new JPanel();
		pCenter.setBounds(-10, 165, 900, 290);
		JScrollPane stList = new JScrollPane(studentList);
		stList.setPreferredSize(new Dimension(875,286));
		pCenter.add(stList);

		add(pCenter);

		//////////////////////////////////////////////////////////////////////

		JPanel pSouth = new JPanel();
		pSouth.setLayout(null);
		pSouth.setBounds(0, 0, 900, 550);

		add(pSouth);

		//////////////////////////////////////////////////////////////////////



	} // end of addLayout()

	class LectureTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "강의번호", "강의명", "강사명", "요일", "교시", "강의실" };

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
	} // end of LectureTableModel()

	/*
	class AttendanceTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "학생이름", "연락처", "출석", "결석", "지각" };

		// 기본적인 TabelModel  만들기
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

		public boolean isCellEditable(int rowIndex, int columnIndex) {
		    return ((columnIndex >= 2) ? true : false);
		}


	} // end of AttendanceTableModel()*/

	class studentModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "학생이름", "연락처", "출석", "지각", "결석" };

		// 기본적인 TabelModel  만들기
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


		// JTable uses this method to determine the default renderer/ editor for
		// each cell. If we didn't implement this method, then the last column
		// would contain text ("true"/"false"), rather than a check box.

		/*
	    public Class getColumnClass(int col) {
	    	if (col >= 2) { 
	            return Boolean.class; 
	         } 
	         else { 
	            return String.class; 
	         } 
	    }*/


		//Don't need to implement this method unless your table's editable.
		/*
		public boolean isCellEditable(int row, int col) {
			//Note that the data/cell address is constant,
			//no matter where the cell appears onscreen.
			if (col >= 2) {
				return true;
			} else {
				return false;
			}

		}
		
	    public void setValueAt(Object obj, int r, int c){
	    	   // 테이블에 입력된 값으로 설정
	    	   String[] row = vt.get(r);
	    	   row[c] = (String)obj;
	    	   vt.addElement(row);
	    	  }
		 */
	}

}