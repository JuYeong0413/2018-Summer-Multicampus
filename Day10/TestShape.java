package day10;

/*
����1. �Ʒ� Ŭ�������̾�׷��� �����ϰ�(���̾�׷����� getter/setter �޼ҵ尡 ������, ���α׷� �ۼ��ÿ� �����ؾ� ��),
TestShape �������� �Ʒ��� ��°���� �������� Shape.java, Circle.java, Rectangular.java�� �ϼ��Ͻÿ�.
- Encapsulation ���� �� ������ private���� �����ϰ�, �� private member variable�� �����ϱ� ����
  getter, setter member method�� �����Ͻÿ�
- Shape�� abstract method�� ���� abstract class�̴�.
- TestShape.java�� ���������� ����ǵ��� Shape.java, Circle.java, Rectangular.java�� ������ �����ڸ� �߰��Ͻÿ�.

<<TestShape.java�� ������>>
���� ������ 314.1592653589793
���簢���� ������ 200.0
*/

public class TestShape {
	public static void main(String[] args) {
		Shape[] shape = new Shape[2];
		
		shape[0] = new Circle("��", 10);
		shape[1] = new Rectangular("���簢��", 10, 20);
		
		for(int i = 0; i < shape.length; i++) {
			shape[i].calculationArea();
			shape[i].print();
		}
	}
}
