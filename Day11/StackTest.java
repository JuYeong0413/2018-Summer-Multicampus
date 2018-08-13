package day11;

public class StackTest {
	public static void main(String[] args) {
		MyStack stack = new MyStack(10);
		if(stack.isEmpty()) {
			System.out.println("스택이 비어있습니다.");
		}
		
		for(int i = 1; i <= 10; i++) {
			stack.push(i);
		}
		
		if(stack.isFull()) {
			System.out.println("스택이 가득 찼습니다.");
		}
		
		System.out.println("최상위 숫자 : " + stack.top());
		System.out.println("최상위에서 꺼낸 숫자 : " + stack.pop());
		System.out.println("최상위에서 꺼낸 숫자 : " + stack.pop());
		System.out.println("");
		System.out.println("== 스택 리스트 ==");
		for(int i = 1; i <= 10; i++) {
			int num = stack.pop();
			if(num != -1)
				System.out.println(num);
		}
	}
}

class MyStack {
	private int size = 0;
	private int[] stack;
	private int index = -1;
	
	public MyStack() {
		size = 10;
		stack = new int[10];
	}
	
	public MyStack(int n) {
		if(n <= 0) {
			size = 10;
			stack = new int[10];
		}
		else {
			size = n;
			stack = new int[n];
		}
	}
	
	public int pop() {
		int num;
		
		if(index == -1) return -1; // 꺼낼 숫자가 없는 경우
		else {
			num = stack[index];
			stack[index] = 0;
			index--;
			return num;
		}
	}
	
	public int top() {
		if(index == -1) return -1; // 꺼낼 숫자가 없는 경우
		else return stack[index];
	}
	
	public void push(int n) {
		index++;
		stack[index] = n;
	}
	
	public boolean isEmpty() {
		if(index == -1) return true;
		else return false;
	}
	
	public boolean isFull() {
		if(index == (size - 1)) return true;
		else return false;
	}
}