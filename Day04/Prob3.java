package day04;

/*
 Prob3 Ŭ������ main() ���� �־��� ���ڿ� �迭�� �����Ͽ�
 �迭�� ������ �������� ������� �� �ֵ��� main �޼��带 �ۼ��ϼ���.
- ���ڿ� �迭�� ������ �������� ����ϵ� �� ���ڿ� ���� �������� ����Ѵ�.
- �Է����� �־��� ���ڿ� �迭�� �� :
	{ "Java Programming" , "JDBC", "Oracle10g", "JSP/Servlet" }
- ó�� ����� �� : �Ʒ� ����.
	gnimmargorP avaJ
	CBDJ
	g01elcarO
	telvreS/PSJ
 */

public class Prob3 {
	public static void main(String[] args) {
		String[] strData  = { "Java Programming" , "JDBC", "Oracle10g", "JSP/Servlet" };
		
		String[] inverseStr = new String[strData.length];
		for(int i = 0; i < inverseStr.length; i++) {
			inverseStr[i] = "";
		}

		for(int i = 0; i < inverseStr.length; i++) {
			for(int j = strData[i].length() - 1; j >= 0; j--) {
				inverseStr[i] += strData[i].charAt(j);
			}
		}
		
		for(String data : inverseStr) {
			System.out.println(data);
		}
	}
}