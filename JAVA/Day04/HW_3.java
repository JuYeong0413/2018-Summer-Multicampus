package day04;

// arr �迭 �� ��� ��� ���� ���ϴ� ���α׷��� �ϼ��Ͻÿ�  .

public class HW_3 {
	public static void main(String[] args) { 
		int[] arr = {10, 20, 30, 40, 50}; 
		
		// TODO arr �迭 �� ��� ��� ���� ���ϴ� ���α׷��� �ϼ�
		int sum = 0;
		
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		
		System.out.println("sum="+sum);
	 } 
}
