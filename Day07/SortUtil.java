package day07;

import java.util.Arrays;

/*
SortUtil Ŭ������ int[] ascending(int[] inputNumbers) �޼ҵ带 �ϼ��Ͻʽÿ�. 
ascending �޼ҵ�� �Էµ� ���ڵ��� ������������ �����Ͽ� �����մϴ�. 

int[] ascending(int[] inputNumbers)
-	���� �迭�� ������ ������������ �����Ͽ� int �迭�� �����ϴ� �޼ҵ带 �����Ѵ�.
-	���� �迭 ���� ������ ���ڴ� �������� �ʴ� ������ �����մϴ�.

<< ���� ���� >>
�Է� �迭 : int[] inputNumbers = {7, 5, 2, 19, 34, 51, 32, 11, 67, 21};
                       ���ϰ� : {2, 5, 7, 11, 19, 21, 32, 34, 51, 67}

���� ���� :
1.	�׽�Ʈ �������� ������ main �޼ҵ带 ������ �� �ֽ��ϴ�.
2.	�־��� �޼ҵ��� signature�� �������� �ʽ��ϴ�.

 */
public class SortUtil {
	public static void main(String[] args) {
		SortUtil util = new SortUtil();
		int[] numbers = new int[] { 7, 5, 2, 19, 34, 51, 32, 11, 67, 21 };
		
		// ascending() �Լ� ȣ�� �� ���ϰ� ����ϴ� �ڵ�
		//System.out.println(Arrays.toString(numbers));
		System.out.println(Arrays.toString(util.ascending(numbers)));
		
	}
	
	public int[] ascending(int[] inputNumbers) {
		// �ڵ� �ϼ�
		int min = 0;
		int j = 0;
		for(int i = 0; i < inputNumbers.length; i++) {
			min = i; // ��ġ index�� ����� ��
			for(j = i + 1; j < inputNumbers.length; j++) {
				if(inputNumbers[min] > inputNumbers[j]) {
					min = j;
				}
			}

			int temp = inputNumbers[i];
			inputNumbers[i] = inputNumbers[min];
			inputNumbers[min] = temp;
			
		}
		return inputNumbers;
	}
}
