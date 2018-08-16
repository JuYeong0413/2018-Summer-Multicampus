package day10;

/*
문제1. 아래 클래스다이어그램을 만족하고(다이어그램에는 getter/setter 메소드가 없으나, 프로그램 작성시에 포함해야 함),
TestShape 실행결과가 아래의 출력결과가 나오도록 Shape.java, Circle.java, Rectangular.java를 완성하시오.
- Encapsulation 위해 각 변수를 private으로 선언하고, 각 private member variable에 접근하기 위한
  getter, setter member method를 선언하시오
- Shape는 abstract method를 갖는 abstract class이다.
- TestShape.java가 정상적으로 수행되도록 Shape.java, Circle.java, Rectangular.java에 적절한 생성자를 추가하시오.

<<TestShape.java의 실행결과>>
원의 면적은 314.1592653589793
직사각형의 면적은 200.0
*/

public class TestShape {
	public static void main(String[] args) {
		Shape[] shape = new Shape[2];
		
		shape[0] = new Circle("원", 10);
		shape[1] = new Rectangular("직사각형", 10, 20);
		
		for(int i = 0; i < shape.length; i++) {
			shape[i].calculationArea();
			shape[i].print();
		}
	}
}
