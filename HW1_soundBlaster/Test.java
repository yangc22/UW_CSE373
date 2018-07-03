
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DStack s = new ArrayStack();
		s.push(1.1);
		s.push(2.1);
		s.push(3.1);
		s.push(4.1);
		s.push(5.1);
		System.out.println(s);
		System.out.println(s.pop());
		System.out.println(s);
		System.out.println(s.pop());
		System.out.println(s);
		System.out.println(s.peek());
		System.out.println(s);
	}

}
