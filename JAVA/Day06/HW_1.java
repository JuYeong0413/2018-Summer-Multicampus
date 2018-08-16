package day06;

/*
두 개의 숫자를 매개 변수로 받아서 두 숫자 사이의 차를 구하되, 
실행 결과가 음수일 경우 양의 정수로 변경하여 리턴하는 abs() 메서드를 구현하시오.
(단, 주어진 메서드의 시그니쳐는 변경하지 않는다.)
*/

public class HW_1 {
	public static void main(String[] args) {
		int num1 = 6, num2 = 15;
		System.out.println("두 수의 차는 : " + HW_1.abs(num1, num2));
	}
	
	private static int abs(int num1, int num2) {
		// 코드 작성
		int result = num1 - num2;
		
		if(result < 0) return -result;
		else return result;
	}
}
