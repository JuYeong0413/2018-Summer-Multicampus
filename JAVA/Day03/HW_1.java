package day03;

// ���ǽ��� ����� ������(�������).

import java.util.Scanner;

public class HW_1 {
	public static void main(String[] args) {		
		Scanner sc = new Scanner(System.in);
		int year;
		System.out.print("������ �Է��ϼ��� : ");
		year = sc.nextInt();
		
		if(year > 0) {
			if((year % 400 == 0) || ((year % 100 != 0) && (year % 4 == 0))) {
				System.out.println(year + "���� �����Դϴ�.");		
			}
			else {
				System.out.println(year + "���� ������ �ƴմϴ�.");
			}
		}
		else {
			System.out.println("�߸��� �Է��Դϴ�.");
		}
	}
}
