package day03;

// Prob2
// 1+(1+2)+(1+2+3)+(1+2+3+4)+...+(1+2+3+...+10) 의 결과를 계산하시오

public class Prob2 {
	public static void main(String[] args) {
		int result = 0;
		int sum = 0;
		
		for(int i = 0; i <= 10; i++) {
			for(int j = 0; j <= i; j++) {
				sum += j;
			}
			result += sum;
			sum = 0;
		}
		System.out.println("결과 : " + result);
	}
}
