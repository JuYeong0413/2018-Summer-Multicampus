package day03;

// 조건식을 만들어 보세요(윤년공식).

import java.util.Scanner;

public class HW_1 {
	public static void main(String[] args) {		
		Scanner sc = new Scanner(System.in);
		int year;
		System.out.print("연도를 입력하세요 : ");
		year = sc.nextInt();
		
		if(year > 0) {
			if((year % 400 == 0) || ((year % 100 != 0) && (year % 4 == 0))) {
				System.out.println(year + "년은 윤년입니다.");		
			}
			else {
				System.out.println(year + "년은 윤년이 아닙니다.");
			}
		}
		else {
			System.out.println("잘못된 입력입니다.");
		}
	}
}
