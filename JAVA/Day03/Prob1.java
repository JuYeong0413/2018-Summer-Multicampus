package day03;

public class Prob1 {
	/*
	  ���� ��ȣ��(Classical Cryptography)���� ����ϴ� ��� �� ��Caesar Cipher�� ��
	  ��ȣȭ�� ����� �Ǵ� ������ ���ڵ��� ���ĺ� ���� ����° ������ ���ڷ� ġȯ�ϴ� ������ ġȯ ��ȣ���
	 (��, a�� d��, b�� e��, �� , x�� a��, y�� b��, z�� c��)�Դϴ�.  
	 ��everyday we have is one more than we deserve�� ��� ���ڿ��� ���� 
	 Caesar Cipher �� �����Ͽ� ��ȣȭ�� ���ڿ� ���� �Ʒ��� ���� ����ϵ��� 
	 Prob4�� Ŭ������ main �Լ��� �ϼ��Ͻʽÿ�. 
	 (��, ���鿡 ���ؼ��� ġȯ�� �������� �ʽ��ϴ�.)
	  ��ȣȭ�� ���ڿ� : everyday we have is one more than we deserve
	  ��ȣȭ�� ���ڿ� : hyhubgdb zh kdyh lv rqh pruh wkdq zh ghvhuyh
	*/

	public static void main(String[] args) {
		String sourceString = "everyday we have is one more than we deserve";
		String encodedString = "";
		
		// ���� : ���� 'a'�� �������� 97�̸�, 'z'�� 122�Դϴ�. 
		
        //TODO
		for(int i = 0; i < sourceString.length(); i++) {
			char ch = sourceString.charAt(i);
			
			// �켱 �ش� ���ڰ� ���ĺ����� üũ
			if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
			{
				if(ch + 3 > 'z') { // x, y, z�� ���� a, b, c�� ���ư��� ���� �κ�
					encodedString += (char)(ch - 23);					
				}
				else { // x, y, z�� ������ ������ ���ĺ� ���ڴ� ����° ������ ���ڷ� ġȯ
					encodedString += (char)(ch + 3);
				}
			}
			
			else { // �����̳� Ư�����ڴ� �״�� ���
				encodedString += Character.toString(ch);
			}
		}

		
		System.out.println("��ȣȭ�� ���ڿ� : " + sourceString);
		System.out.println("��ȣȭ�� ���ڿ� : " + encodedString);
	}
}