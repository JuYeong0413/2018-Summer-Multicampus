package day03;

// 1���� 20������ ���� �߿��� 2�Ǵ� 3�� ����� �ƴ� ���� ������ ���Ͻÿ�  .

public class HW_2 {
	public static void main(String[] args) {		
		int sum = 0;
		
		//ToDo
		for(int i = 1; i <= 20; i++) {
			if((i % 2 == 0) || (i % 3 == 0)) continue;
			else {
				sum += i;
			}
		}
		
		System.out.println("sum="+sum);
	}
}
