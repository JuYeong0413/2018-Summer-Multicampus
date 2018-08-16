package day04;

import java.util.Scanner;

// 문자열 참조변수  msg 가 "yes" 일때 true 인  조건식 

public class HW_1 {
	public static void main(String[] args) {
		String msg;
		
		System.out.println("문자열을 입력하세요 : ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		if(input.equals("yes")) {
			System.out.println("true");
		}
		else System.out.println("false");
		
		input = null;
		sc.close();
	}
}
