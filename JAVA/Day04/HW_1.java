package day04;

import java.util.Scanner;

// ���ڿ� ��������  msg �� "yes" �϶� true ��  ���ǽ� 

public class HW_1 {
	public static void main(String[] args) {
		String msg;
		
		System.out.println("���ڿ��� �Է��ϼ��� : ");
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
