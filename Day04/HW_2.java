package day04;

// 1+(-2)+3+(-4)+... �� ���� ������ ��� ���س����� ��
// ����� ���ؾ� ������  100  �̻��� �Ǵ��� ���Ͻÿ� .

public class HW_2 {
	public static void main(String[] args) {
		boolean flag = true;
		int count = 0;
		int sum = 0;
		
		while(flag) {
			if(count % 2 == 0) { // ¦���� ��
				sum -= count;
			}
			else { // Ȧ���� ��
				sum += count;
			}
			
			if(sum >= 100) {
				System.out.println(count + "���� ���ϸ� ������ " + sum);
				flag = false;
			}
			count++;
		}
	}
}
