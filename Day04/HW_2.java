package day04;

// 1+(-2)+3+(-4)+... 과 같은 식으로 계속 더해나갔을 때
// 몇까지 더해야 총합이  100  이상이 되는지 구하시오 .

public class HW_2 {
	public static void main(String[] args) {
		boolean flag = true;
		int count = 0;
		int sum = 0;
		
		while(flag) {
			if(count % 2 == 0) { // 짝수일 때
				sum -= count;
			}
			else { // 홀수일 때
				sum += count;
			}
			
			if(sum >= 100) {
				System.out.println(count + "까지 더하면 총합이 " + sum);
				flag = false;
			}
			count++;
		}
	}
}
