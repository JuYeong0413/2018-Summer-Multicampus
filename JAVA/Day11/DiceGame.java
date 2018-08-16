package day11;

class Dice {
	int size;
	Dice(int size) {
		this.size = size;
	}
	
	int play() {
		int number = (int)(Math.random() * size) + 1;
		return number;
	}
}

public class DiceGame {
	public static void main(String args[]) {
		DiceGame game = new DiceGame();
		
		int result1 = game.countSameEye(10);
		System.out.println("면의 개수가 8개인 주사위 2개를 던져서 같은 눈이 나온 횟수 : " + result1);
		
		int result2 = game.countSameEye(-10);
		System.out.println("면의 개수가 8개인 주사위 2개를 던져서 같은 눈이 나온 횟수 : " + result2);
		
	}
	
	int countSameEye(int n) {
		//구현
		
		Dice d1 = new Dice(8);
		Dice d2 = new Dice(8);
		
		int count = 0;
		
		try {
			if(n <= 0) throw new IllegalArgumentException();
			
			for(int i = 0; i < n; i++) {
				int a = d1.play();
				int b = d2.play();
				
				if(a == b)	++count;
			}
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		return count;
	}
}