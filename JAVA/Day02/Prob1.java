package day02;

public class Prob1 {
	public static void main(String[] args) {
/*
�Ʒ��� ���� num�� ��������  ��� ����  0�� ����ϴ�  �ڵ��̴�.
���� �����ڸ� �̿��ؼ� �� �˸��� �ڵ带 �ϼ��ϼ���. 
��Ʈ:   ���� �����ڸ� �ι� �̿�
*/
	int num = -90;
	System.out.println(num > 0? "���":(num < 0? "����":"0"));


/*
������ �빮�ڸ� �ҹ��ڷ� �����ϴ� �ڵ��Դϴ�.
���� ch�� ����� ���ڰ� �빮�� �� ��쿡�� �ҹ��ڷ� �����ϴ� �ڵ带 �ϼ� �մϴ�.
*/
	char ch = 'P';
	char lowerCase = (ch >= 65 && ch <= 90)? Character.toLowerCase(ch):ch;
	System.out.print("ch:"+ch);
	System.out.println(" to lowerCase :"+lowerCase);
	}
}