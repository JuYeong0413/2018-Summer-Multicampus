package day06;

/*
Prob2���� leftPad �޼ҵ带 �ۼ��Ͻʽÿ�. leftPad �޼ҵ��� �Ӽ��� ������ �����ϴ�.
1. �޼ҵ� �ñ״�ó : public String leftPad(String str, int size, char padChar)
2. �Է� ���ڿ�(str)�� ���̰� �Է¹��� ��(size)�� �� ������ ���ڿ��� ������ ������ ����(padChar)�� ä�� �� �����Ѵ�.
3. ��, �Է� ���ڿ�(str)�� ���̰� �Է¹��� ��(size) ���� ū ��쿡�� ���� ���ڿ�(str)�� �״�� �����Ѵ�.

���� ��)
leftPad(��MDS��, 6, ��#��) ###MDS 
leftPad(��MDS��, 5, ��$��) $$MDS
leftPad(��MDS��, 2, ��&��) MDS

���� ��� ȭ��
###MDS 
$$MDS
MDS
*/

public class Prob2 {
	public static void main(String args[]) {
		Prob2 p = new Prob2();
		
		System.out.println( p.leftPad("MDS", 6, '#') );
		System.out.println( p.leftPad("MDS", 5, '$') ); 
		System.out.println( p.leftPad("MDS", 2, '&') ); 
	}
	public String leftPad(String str, int size, char padChar) {
		/*  ���⿡ ���α׷��� �ϼ��Ͻʽÿ�. */
		if(str.length() >= size) {
			return str;
		}
		else {
			String s = new String();
			for(int i = 0; i < size - str.length(); i++) {
				s += Character.toString(padChar);
			}
			return s.concat(str);
		}
		
	}
}
