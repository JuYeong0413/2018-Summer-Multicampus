package day10;

// ����3. Outer Ŭ������ ���� Ŭ���� Inner�� ������� iv�� ���� ����ϴ� �ڵ带 �ۼ��Ͻÿ�.

class Outer {
	class Inner {
		int iv = 100;
	}
}

class Ex2 {
	public static void main(String[] args) {
		// �ڵ� �ۼ�
		Outer o = new Outer();
		Outer.Inner i = o.new Inner();
		System.out.println(i.iv);
	}
}
