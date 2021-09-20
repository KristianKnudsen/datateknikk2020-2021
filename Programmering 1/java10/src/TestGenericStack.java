
public class TestGenericStack {

	public static void main(String[] args) {
		GenericArrayStack<String> gsString = new GenericArrayStack<>();

		gsString.push("one");
		gsString.push("two");
		gsString.push("three");

		gsString.pop();


		System.out.println(gsString.getSize());

		System.out.println(gsString.toString());

//		while (!(gsString.isEmpty()))
//		   System.out.println(gsString.pop());
//		System.out.println(gsString.toString());
//
//		GenericArrayStack<Integer> gsInteger = new GenericArrayStack<>();
//		gsInteger.push(1);
//		gsInteger.push(2);
//		gsInteger.push(3);
//		//gsInteger.push("4");
//
//		System.out.println(gsInteger.toString());
//		while (!(gsInteger.isEmpty()))
//		   System.out.println(gsInteger.pop());


	}
}
