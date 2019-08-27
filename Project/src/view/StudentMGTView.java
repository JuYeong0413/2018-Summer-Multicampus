package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import model.StudentMGTModel;
import model.academy.Lecture;
import model.academy.Student;


public class StudentMGTView extends JPanel {
	// member field
	JTextField tfStudentName, tfStudentTel;
	JButton bSearch, bDrop;

	JTable lectureList, timeTable;

	LecTableModel lecTM;
	//timeTableModel timeTM;
	DefaultTableModel model;

	StudentMGTModel db;

	JLabel []telLabel;
	JRadioButton []telButton;
	Object []selectTel;

	String selectedLec;

	public StudentMGTView() {
		addLayout();
		connectDB();
		eventProc();
	} // end of StudentMGTView()

	void eventProc() {
		bSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tel = tfStudentTel.getText();
				String name = tfStudentName.getText();
				
				if(tel.isEmpty() && name.isEmpty()) return; // 비어있는데 버튼을 누른 경우 아무 동작도 하지 않음
				else if(!tel.isEmpty() && name.isEmpty()) {
					// 전화번호만 입력해서 찾는 경우
					searchByTel();
				}
				else if(tel.isEmpty() && !name.isEmpty()) {
					// 이름만 입력해서 찾는 경우
					searchByName();
				}
				else searchList(); // 이름 + 전화번호로 찾는 경우
			}
		});

		lectureList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				int row = lectureList.getSelectedRow();
				int col = 0;
				selectedLec = (String) lectureList.getValueAt(row, col);
				selectedLec = selectedLec.trim();
			}
		});

		bDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(tfStudentTel.getText().isEmpty()) return; // 비어있는데 버튼을 누른 경우 아무 동작도 하지 않음
				deleteConfirm();
			}
		});
	} // end of eventProc()

	public void deleteConfirm() {
		int n = JOptionPane.showConfirmDialog(
				null,
				"강의 수강을 취소하시겠습니까?",
				"",
				JOptionPane.YES_NO_OPTION);

		if(n == JOptionPane.YES_OPTION) {
			int lecNum = Integer.parseInt(selectedLec);
			try {
				db.decreaseCapacity(lecNum);
				db.dropLecture(lecNum);
				db.registerCancel(lecNum, tfStudentTel.getText());
				lecTM.fireTableDataChanged();
				searchByTel();

			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else { // n == JOptionPane.NO_OPTION
			return;
		}
	}

	public void searchList() {
		String tel = tfStudentTel.getText();
		String name = tfStudentName.getText();
		try {
			Student s = db.searchList(tel, name);
			if(s != null) {
				searchByTel();
			}
			else JOptionPane.showMessageDialog(null, "수강생 정보가 없습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // end of searchList()

	public void loadList(String tel) { // TODO
		try {
			ArrayList result = db.getList(tel);

			if(result.isEmpty()) {
				JOptionPane.showMessageDialog(null, "수강중인 강의가 없습니다.");
			} else {
				lecTM.data = result;
				lecTM.fireTableDataChanged();
				loadTimeTable(tel);
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "검색실패");
			e.printStackTrace();
		}
	} // end of loadList()

	public void loadTimeTable(String tel) {
		try {
			ArrayList result = db.getTimeTable(tel);
			String[] data = new String[result.size()]; // 각각의 강의 정보(요일, 교시)를 저장할 String 배열

			for(int i = 0; i < result.size(); i++) {
				String temp = result.get(i).toString();
				data[i] = temp.substring(1, temp.length() - 1);
			}

			paintTable(data);
			//.data = result;
			//.fireTableDataChanged();
			timeTable.updateUI();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "검색실패");
			e.printStackTrace();
		}
	} // end of loadTimeTable()

	public void searchByTel() {
		String tel = tfStudentTel.getText();
		try {
			Student s = db.selectByTel(tel);

			if(s == null) {
				noData();
			} else {
				tfStudentName.setText(s.getStudentName());
				tfStudentTel.setText(s.getStudentTel());
				// 수강하는 강의 정보 불러오기
				loadList(tel);
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "검색실패");
			e.printStackTrace();
		}
	} // end of searchByTel()

	public void searchByName() {
		String name = tfStudentName.getText();
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
				// 수강하는 강의 정보 불러오기
				loadList(tel);
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
					loadList(tfStudentTel.getText());
				}			
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "검색실패");
			e.printStackTrace();
		}
	} // end of searchByName()


	public void paintTable(String []data) throws ClassNotFoundException {
		TableCellRenderer renderer = new Renderer();
		Renderer r = new Renderer();
		timeTable.setDefaultRenderer(Class.forName("java.lang.Object"), renderer);
		
		for(int i = 0; i < data.length; i++) {
			if(data[i].contains("월")) {
				if(data[i].contains("1")) timeTable.getCellRenderer(1, 1);
				else if(data[i].contains("2")) timeTable.getCellRenderer(1, 2);
				else if(data[i].contains("3")) timeTable.getCellRenderer(1, 3);
				else if(data[i].contains("4")) timeTable.getCellRenderer(1, 4);
				else if(data[i].contains("5")) timeTable.getCellRenderer(1, 5);
			}
			/*
			if(data[i].contains("화")) {
				if(data[i].contains("1")) r.setNum(2, 1);
				else if(data[i].contains("2")) r.setNum(2, 2);
				else if(data[i].contains("3")) r.setNum(2, 3);
				else if(data[i].contains("4")) r.setNum(2, 4);
				else if(data[i].contains("5")) r.setNum(2, 5);
			}
			if(data[i].contains("수")) {
				if(data[i].contains("1")) r.setNum(3, 1);
				else if(data[i].contains("2")) r.setNum(3, 2);
				else if(data[i].contains("3")) r.setNum(3, 3);
				else if(data[i].contains("4")) r.setNum(3, 4);
				else if(data[i].contains("5")) r.setNum(3, 5);
			}
			if(data[i].contains("목")) {
				if(data[i].contains("1")) r.setNum(4, 1);
				else if(data[i].contains("2")) r.setNum(4, 2);
				else if(data[i].contains("3")) r.setNum(4, 3);
				else if(data[i].contains("4")) r.setNum(4, 4);
				else if(data[i].contains("5")) r.setNum(4, 5);
			}
			if(data[i].contains("금")) {
				if(data[i].contains("1")) r.setNum(5, 1);
				else if(data[i].contains("2")) r.setNum(5, 2);
				else if(data[i].contains("3")) r.setNum(5, 3);
				else if(data[i].contains("4")) r.setNum(5, 4);
				else if(data[i].contains("5")) r.setNum(5, 5);
			}*/
		}
	}

	public int rowNum(int row) {

		return 0;
	}

	void connectDB() {
		try {
			db = new StudentMGTModel();
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // end of connectDB();

	public void noData() {
		JOptionPane.showMessageDialog(null,
				"학생 정보가 없습니다.",
				"error",
				JOptionPane.ERROR_MESSAGE);
	} // end of noData()

	void addLayout() {
		setLayout(null);
		setBounds(0, 0, 900, 550);

		tfStudentName = new JTextField(20);
		tfStudentTel  = new JTextField(20);
		bSearch		  = new JButton("조회");
		bDrop 		  = new JButton("선택한 강의 수강취소");

		lecTM     = new LecTableModel();
		lectureList   = new JTable(lecTM);
		//timeTable	  = new JTable(timeTM);

		// 화면붙이기
		// 위쪽 영역
		JPanel pNorth = new JPanel();
		pNorth.setLayout(null);
		TitledBorder oneTb = new TitledBorder("수강생조회");
		pNorth.setBorder(oneTb);
		pNorth.setBounds(0, 0, 900, 110);

		JLabel tel  = new JLabel("연락처");
		JLabel name = new JLabel("이    름");

		Font f = new Font("Dialog", Font.BOLD, 13);

		tel.setFont(f);
		name.setFont(f);

		tel.setBounds(40, 30, 100, 20);
		name.setBounds(40, 65, 100, 20);

		tfStudentTel.setBounds(130, 28, 550, 25);
		tfStudentName.setBounds(130, 62, 550, 25);

		bSearch.setFont(f);
		bSearch.setBounds(720, 28, 120, 59);

		pNorth.add(tel);
		pNorth.add(tfStudentTel);
		pNorth.add(bSearch);
		pNorth.add(name);
		pNorth.add(tfStudentName);

		//////////////////////////////////////////////////////////////////////

		// 아래쪽 영역
		JPanel pSouth = new JPanel();
		pSouth.setLayout(null);
		pSouth.setBounds(0, 110, 900, 440);
		// 왼쪽 영역

		JPanel pSouthLeft = new JPanel();
		pSouthLeft.setLayout(new BorderLayout());

		//pSouthButton.setLayout(new FlowLayout());

		JScrollPane stList = new JScrollPane(lectureList);
		//stList.setPreferredSize(new Dimension(450, 400));

		bDrop.setBounds(0, 100, 10, 30);

		pSouthLeft.add(stList, BorderLayout.CENTER);
		pSouthLeft.add(bDrop, BorderLayout.SOUTH);
		pSouthLeft.setBounds(0, 0, 430, 370);

		//////////////////////////////////////////////////////////////////////

		// 오른쪽 영역
		JPanel pSouthRight = new JPanel();
		pSouthRight.setLayout(new BorderLayout());

		String []colNames = { "교시", "월", "화", "수", "목", "금" };
		Object [][]data = {
				{ "1", "", "", "", "", ""},
				{ "2", "", "", "", "", ""},
				{ "3", "", "", "", "", ""},
				{ "4", "", "", "", "", ""},
				{ "5", "", "", "", "", ""}

		};

		timeTable = new JTable(data, colNames);
		DefaultTableCellRenderer cellAlignCenter = new DefaultTableCellRenderer();
		cellAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		timeTable.getColumn("교시").setCellRenderer(cellAlignCenter);
		timeTable.setRowHeight(70);
		timeTable.getColumnModel().getColumn(0).setPreferredWidth(30);

		TableColumnModel tcm = lectureList.getColumnModel();
		for(int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(cellAlignCenter);
		}

		JScrollPane timeList = new JScrollPane(timeTable);
		timeList.setPreferredSize(new Dimension(450, 400));


		pSouthRight.add(timeList);
		pSouthRight.setBounds(450, 0, 430, 373);

		//////////////////////////////////////////////////////////////////////

		add(pNorth);
		pSouth.add(pSouthRight);
		pSouth.add(pSouthLeft);
		add(pSouth);

	} // end of addLayout()

	// 화면에 테이블 붙이는 메소드
	class LecTableModel extends AbstractTableModel {
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
	}
	
}

class Renderer extends DefaultTableCellRenderer
{	
	int colNum, rowNum;
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus,
			int row, int column)
	{
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		//setBackground(Color.LIGHT_GRAY);
		//c.setBackground(Color.GRAY);

		//timeTableModel ttm = (timeTableModel) table.getModel();

		//if (column == getCol() && row == getRow()) {
			setBackground(Color.LIGHT_GRAY);
		//}

		return this;
	}

}