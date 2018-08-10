package day03;

public class Prob1 {
	/*
	  고전 암호학(Classical Cryptography)에서 사용하는 기법 중 “Caesar Cipher” 는
	  암호화의 대상이 되는 각각의 문자들을 알파벳 상의 세번째 오른쪽 문자로 치환하는 간단한 치환 암호기법
	 (즉, a는 d로, b는 e로, … , x는 a로, y는 b로, z는 c로)입니다.  
	 “everyday we have is one more than we deserve” 라는 문자열에 대해 
	 Caesar Cipher 를 적용하여 암호화된 문자열 값을 아래와 같이 출력하도록 
	 Prob4의 클래스의 main 함수를 완성하십시오. 
	 (단, 공백에 대해서는 치환을 적용하지 않습니다.)
	  암호화할 문자열 : everyday we have is one more than we deserve
	  암호화된 문자열 : hyhubgdb zh kdyh lv rqh pruh wkdq zh ghvhuyh
	*/

	public static void main(String[] args) {
		String sourceString = "everyday we have is one more than we deserve";
		String encodedString = "";
		
		// 참고 : 문자 'a'의 정수값은 97이며, 'z'는 122입니다. 
		
        //TODO
		for(int i = 0; i < sourceString.length(); i++) {
			char ch = sourceString.charAt(i);
			
			// 우선 해당 문자가 알파벳인지 체크
			if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
			{
				if(ch + 3 > 'z') { // x, y, z는 각각 a, b, c로 돌아가기 위한 부분
					encodedString += (char)(ch - 23);					
				}
				else { // x, y, z를 제외한 나머지 알파벳 문자는 세번째 오른쪽 문자로 치환
					encodedString += (char)(ch + 3);
				}
			}
			
			else { // 공백이나 특수문자는 그대로 출력
				encodedString += Character.toString(ch);
			}
		}

		
		System.out.println("암호화할 문자열 : " + sourceString);
		System.out.println("암호화된 문자열 : " + encodedString);
	}
}