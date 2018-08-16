package day05;

/*
	Prob4 Ŭ���� ������ �����ϼ���.
	int[] num = new int[6]
	�迭 num�� ���� 1~45 ������ ������ ����ִ´�.(�̶� �ߺ������Ѵ�.)
	������ ȭ�鿡 ����Ѵ�.
*/

public class Prob4 {
	public static void main(String[] args) {
		int[] num = new int[6];
		int randomNumber;
		boolean sameNumber = false;
		
		for(int i = 0; i < num.length; i++) {
			randomNumber = (int)((Math.random() * 100) % 45 + 1); // ���� ����
			
			// ������ ������ �ߺ����� Ȯ��
			for(int j = 0; j < i; j++) {
				if(randomNumber == num[j]) {
					sameNumber = true;
					break;
				}
				else sameNumber = false;
			}
			
			if(!sameNumber) { // �ߺ��� �ƴ� ���
				num[i] = randomNumber;
			}
			else { // �ߺ��� ���
				i--; // Ƚ�� ����
			}
		}
		// ���� �� ��� ���
		for(int d : num) System.out.print(d + " ");
		System.out.println();
		
		// ���� ����
		for(int i = 0; i < num.length - 1; i++) {
			int min = num[i];
			int place = i;
			
			for(int j = i; j < num.length - 1; j++) { // �ּҰ� ã��
				if(min > num[j + 1]) {
					min = num[j + 1];
					place = j + 1;
				}
			}
			// ������ ��ȯ
			int temp = num[i];
			num[i] = min;
			num[place] = temp;
		}
		
		// ���� �� ��� ���
		for(int data : num) System.out.print(data + " ");
	}
}
