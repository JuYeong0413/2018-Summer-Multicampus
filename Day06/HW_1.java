package day06;

/*
�� ���� ���ڸ� �Ű� ������ �޾Ƽ� �� ���� ������ ���� ���ϵ�, 
���� ����� ������ ��� ���� ������ �����Ͽ� �����ϴ� abs() �޼��带 �����Ͻÿ�.
(��, �־��� �޼����� �ñ״��Ĵ� �������� �ʴ´�.)
*/

public class HW_1 {
	public static void main(String[] args) {
		int num1 = 6, num2 = 15;
		System.out.println("�� ���� ���� : " + HW_1.abs(num1, num2));
	}
	
	private static int abs(int num1, int num2) {
		// �ڵ� �ۼ�
		int result = num1 - num2;
		
		if(result < 0) return -result;
		else return result;
	}
}
