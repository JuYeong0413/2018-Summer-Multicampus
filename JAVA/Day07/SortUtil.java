package day07;

import java.util.Arrays;

/*
SortUtil 클래스의 int[] ascending(int[] inputNumbers) 메소드를 완성하십시오. 
ascending 메소드는 입력된 숫자들을 오름차순으로 정렬하여 리턴합니다. 

int[] ascending(int[] inputNumbers)
-	숫자 배열의 순서를 오름차순으로 정렬하여 int 배열로 리턴하는 메소드를 구현한다.
-	숫자 배열 내에 동일한 숫자는 존재하지 않는 것으로 간주합니다.

<< 실행 예시 >>
입력 배열 : int[] inputNumbers = {7, 5, 2, 19, 34, 51, 32, 11, 67, 21};
                       리턴값 : {2, 5, 7, 11, 19, 21, 32, 34, 51, 67}

주의 사항 :
1.	테스트 목적으로 스스로 main 메소드를 구현할 수 있습니다.
2.	주어진 메소드의 signature는 변경하지 않습니다.

 */
public class SortUtil {
	public static void main(String[] args) {
		SortUtil util = new SortUtil();
		int[] numbers = new int[] { 7, 5, 2, 19, 34, 51, 32, 11, 67, 21 };
		
		// ascending() 함수 호출 후 리턴값 출력하는 코드
		//System.out.println(Arrays.toString(numbers));
		System.out.println(Arrays.toString(util.ascending(numbers)));
		
	}
	
	public int[] ascending(int[] inputNumbers) {
		// 코드 완성
		int min = 0;
		int j = 0;
		for(int i = 0; i < inputNumbers.length; i++) {
			min = i; // 위치 index가 들어가줘야 함
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
