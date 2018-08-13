package day11;

public class StackTest {
	public static void main(String[] args) {
		MyStack stack = new MyStack(10);
		if(stack.isEmpty()) {
			System.out.println("������ ����ֽ��ϴ�.");
		}
		
		for(int i = 1; i <= 10; i++) {
			stack.push(i);
		}
		
		if(stack.isFull()) {
			System.out.println("������ ���� á���ϴ�.");
		}
		
		System.out.println("�ֻ��� ���� : " + stack.top());
		System.out.println("�ֻ������� ���� ���� : " + stack.pop());
		System.out.println("�ֻ������� ���� ���� : " + stack.pop());
		System.out.println("");
		System.out.println("== ���� ����Ʈ ==");
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
		
		if(index == -1) return -1; // ���� ���ڰ� ���� ���
		else {
			num = stack[index];
			stack[index] = 0;
			index--;
			return num;
		}
	}
	
	public int top() {
		if(index == -1) return -1; // ���� ���ڰ� ���� ���
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