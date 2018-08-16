package day05;

/*
	Prob4 클래스 만든후 제출하세요.
	int[] num = new int[6]
	배열 num에 정수 1~45 사이의 난수를 집어넣는다.(이때 중복제거한다.)
	정렬후 화면에 출력한다.
*/

public class Prob4 {
	public static void main(String[] args) {
		int[] num = new int[6];
		int randomNumber;
		boolean sameNumber = false;
		
		for(int i = 0; i < num.length; i++) {
			randomNumber = (int)((Math.random() * 100) % 45 + 1); // 난수 생성
			
			// 생성된 난수가 중복인지 확인
			for(int j = 0; j < i; j++) {
				if(randomNumber == num[j]) {
					sameNumber = true;
					break;
				}
				else sameNumber = false;
			}
			
			if(!sameNumber) { // 중복이 아닌 경우
				num[i] = randomNumber;
			}
			else { // 중복인 경우
				i--; // 횟수 제거
			}
		}
		// 정렬 전 결과 출력
		for(int d : num) System.out.print(d + " ");
		System.out.println();
		
		// 선택 정렬
		for(int i = 0; i < num.length - 1; i++) {
			int min = num[i];
			int place = i;
			
			for(int j = i; j < num.length - 1; j++) { // 최소값 찾기
				if(min > num[j + 1]) {
					min = num[j + 1];
					place = j + 1;
				}
			}
			// 데이터 교환
			int temp = num[i];
			num[i] = min;
			num[place] = temp;
		}
		
		// 정렬 후 결과 출력
		for(int data : num) System.out.print(data + " ");
	}
}
