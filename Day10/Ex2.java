package day10;

// 문제3. Outer 클래스의 내부 클래스 Inner의 멤버변수 iv의 값을 출력하는 코드를 작성하시오.

class Outer {
	class Inner {
		int iv = 100;
	}
}

class Ex2 {
	public static void main(String[] args) {
		// 코드 작성
		Outer o = new Outer();
		Outer.Inner i = o.new Inner();
		System.out.println(i.iv);
	}
}
