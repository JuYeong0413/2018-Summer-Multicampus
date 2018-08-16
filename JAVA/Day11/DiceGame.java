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
		System.out.println("���� ������ 8���� �ֻ��� 2���� ������ ���� ���� ���� Ƚ�� : " + result1);
		
		int result2 = game.countSameEye(-10);
		System.out.println("���� ������ 8���� �ֻ��� 2���� ������ ���� ���� ���� Ƚ�� : " + result2);
		
	}
	
	int countSameEye(int n) {
		//����
		
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