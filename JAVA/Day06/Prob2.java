package day06;

/*
Prob2내에 leftPad 메소드를 작성하십시오. leftPad 메소드의 속성은 다음과 같습니다.
1. 메소드 시그니처 : public String leftPad(String str, int size, char padChar)
2. 입력 문자열(str)의 길이가 입력받은 수(size)가 될 때까지 문자열의 좌측을 지정된 문자(padChar)로 채운 후 리턴한다.
3. 단, 입력 문자열(str)의 길이가 입력받은 수(size) 보다 큰 경우에는 원본 문자열(str)을 그대로 리턴한다.

실행 예)
leftPad(“MDS”, 6, ‘#’) ###MDS 
leftPad(“MDS”, 5, ‘$’) $$MDS
leftPad(“MDS”, 2, ‘&’) MDS

실행 결과 화면
###MDS 
$$MDS
MDS
*/

public class Prob2 {
	public static void main(String args[]) {
		Prob2 p = new Prob2();
		
		System.out.println( p.leftPad("MDS", 6, '#') );
		System.out.println( p.leftPad("MDS", 5, '$') ); 
		System.out.println( p.leftPad("MDS", 2, '&') ); 
	}
	public String leftPad(String str, int size, char padChar) {
		/*  여기에 프로그램을 완성하십시오. */
		if(str.length() >= size) {
			return str;
		}
		else {
			String s = new String();
			for(int i = 0; i < size - str.length(); i++) {
				s += Character.toString(padChar);
			}
			return s.concat(str);
		}
		
	}
}
