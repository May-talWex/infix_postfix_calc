/**
 * This is a testing framework. Use it extensively to verify that your code is working
 * properly.
 */
public class Tester { 

	private static boolean testPassed = true;
	private static int testNum = 0;
	
	/**
	 * This entry function will test all classes created in this assignment.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
	//BinaryOp
		testAddOp();
		testDivideOp();
		testPowOp();
		testMultiplyOp();
		testSubtractOp();
	//Calculators
		testStackCalculator();
		testTreeCalculator();
	//Other
		testExpTokenizer();

		
		
		// Notifying the user that the code have passed all tests. 
		if (testPassed) {
			System.out.println("All " + testNum + " tests passed!");
		}
	}

	/**
	 * This utility function will count the number of times it was invoked. 
	 * In addition, if a test fails the function will print the error message.  
	 * @param exp The actual test condition
	 * @param msg An error message, will be printed to the screen in case the test fails.
	 */
	private static void test(boolean exp, String msg) {
		testNum++;
		
		if (!exp) {
			testPassed = false;
			System.out.println("Test " + testNum + " failed: "  + msg);
		}
	}

	/**
	 * Checks the AddOp class.
	 */
	private static void testAddOp() {
		AddOp op = new AddOp();
		test((op.toString().equals("+")), "The string + should be printed.");
		test((op.operate(1.0 , 2.5) == 3.5), "The answer should be 3.5 .");
		test((op.operate(4.0, 6)) == 10.0 , "The answer should be: 10.0");
		test((op.operate(-4, 6.0)) == 2.0 , "The answer should be: -2.0");
		test((op.operate(20, 6.0)) == 26.0 , "The answer should be: 26.0");
		test((op.operate(15.0, 5)) == 20.0 , "The answer should be: 20.0");
	}

	/**
	 * Checks the DivideOp class.
	 */
	private static void testDivideOp() {
		DivideOp op = new DivideOp();
		test((op.toString().equals("/")), "The string / should be printed.");
		test((op.operate(4.0 , 2.0) == 2.0), "The answer should be 2.0.");
		test((op.operate(7.0 , 2.0) == 3.5), "The answer should be 3.5.");
		test((op.operate(12 , 6.0) == 2.0), "The answer should be 2.0.");
		test((op.operate(40 , 2.0) == 20.0), "The answer should be 2.0.");
		test((op.operate(-10 , 2) == -5.0), "The answer should be -5.0.");
	}

	/**
	 * Checks the MultiplyOp class.
	 */
	private static void testMultiplyOp() {
		MultiplyOp op = new MultiplyOp();
		test((op.toString().equals("*")), "The string * should be printed.");
		test((op.operate(4.0 , 2.0) == 8.0), "The answer should be 8.0.");
		test((op.operate(2.0 , 2.0) == 4.0), "The answer should be 4.0.");
		test((op.operate(-40 , 2) == -80.0), "The answer should be -80.0.");
		test((op.operate(-4.0 , -2.0) == 8.0), "The answer should be 8.0.");
	}

	/**
	 * Checks the PowOp class.
	 */
	private static void testPowOp() {
		PowOp op = new PowOp();
		test((op.toString().equals("^")), "The string ^ should be printed.");
		test((op.operate(4.0 , 2.0) == 16.0), "The answer should be 16.0.");
		test((op.operate(4.0 , -1.0) == 0.25), "The answer should be 16.0.");
		test((op.operate(100 , -1.0) == 0.01), "The answer should be 16.0.");
		test((op.operate(-16 , 2) == 256.0), "The answer should be 256.0.");
	}

	/**
	 * Checks the SubtractOp class.
	 */
	private static void testSubtractOp() {
		SubtractOp op = new SubtractOp();
		test((op.toString().equals("-")), "The string - should be printed.");
		test((op.operate(4.0 , 2.0) == 2.0), "The answer should be 2.0.");
		test((op.operate(-4.0 , 2.0) == -6.0), "The answer should be -6.0.");
		test((op.operate(-4.0 , -2.0) == -2.0), "The answer should be -2.0.");
		test((op.operate(4.0 , -2.0) == 6.0), "The answer should be 6.0.");
		test((op.operate(4.5 , 2.0) == 2.5), "The answer should be 2.0.");
	}

	/**
	 * Checks the StackCalculator class.
	 */
	private static void testStackCalculator() {
		
		StackCalculator pc = new StackCalculator();
		
		String postExp = pc.infixToPostfix("2 + 3");
		String postExp1 = pc.infixToPostfix("2 ^ ( 3 ^ 2 )");
		String postExp2 = pc.infixToPostfix("( 5 + 7 ) * ( 2 - 1 )");
		String postExp3= pc.infixToPostfix("( ( ( ( 3 - 1 ) * ( 4 - 2 ) ) / 2 ) + 3 )");
		String postExp4= pc.infixToPostfix("40 - ( 12 * 2 ) / ( 2 - 1 )");

		test(postExp.equals("2.0 3.0 +") , "The output of \"2 3 -\" should be  2.0 3.0 +");
		test(postExp1.equals("2.0 3.0 2.0 ^ ^") , "The output should be  2.0 3.0 2.0 ^ ^");
		test(postExp2.equals("5.0 7.0 + 2.0 1.0 - *") , "The output should be  5.0 7.0 + 2.0 1.0 - *");
		test(postExp3.equals(("3.0 1.0 - 4.0 2.0 - * 2.0 / 3.0 + ")) , "The answer should be: 3.0 1.0 - 4.0 2.0 - * 2.0 / 3.0 + ");
		test(postExp4.equals(("40.0 12.0 2.0 * 2.0 1.0 - / -")) , "The answer should be: 40.0 12.0 2.0 * 2.0 1.0 - / -");

		double result = pc.evaluate(postExp);
		double result1 = pc.evaluate(postExp1);
		double result2 = pc.evaluate(postExp2);
		double result3 = pc.evaluate(postExp3);

		test(result ==  5.0, "The output of \"2 3 -\" should be 5.0");
		test(result1 ==  512.0, "The output should be 512.0");
		test(result2 ==  12.0, "The output should be 12.0");
		test(result3 == 5.0, "the output should be 5.0");
	}

	/**
	 * Checks the TreeCalculator class.
	 */
	private static void testTreeCalculator() {
		TreeCalculator TreeCalculator = new TreeCalculator();
		String str1 = "5";
		TreeCalculator.evaluate(str1);
		test((TreeCalculator.getInfix().equals("5.0")) , "The answer should be: 5.0 ");
		test((TreeCalculator.getPostfix().equals("5.0")) , "The answer should be: 5.0 ");
		test((TreeCalculator.getPrefix().equals("5.0")) , "The answer should be: 5.0 ");
		test((TreeCalculator.evaluate(str1)) == 5.0 , "The answer should be: 5.0 ");
		String str2 = "1 6 3 * +";
		TreeCalculator.evaluate(str2);
		test((TreeCalculator.getInfix().equals("( 1.0 + ( 6.0 * 3.0 ) )")) , "The answer should be:( 1.0 + ( 6.0 * 3.0 ) )");
		test((TreeCalculator.getPostfix().equals("1.0 6.0 3.0 * +")) , "The answer should be: 1.0 6.0 3.0 * +");
		test((TreeCalculator.getPrefix().equals("+ 1.0 * 6.0 3.0")) , "The answer should be: + 1.0 * 6.0 3.0");
		test((TreeCalculator.evaluate(str2)) == 19.0 , "The answer should be: 19.0");
		String str3 = "6.0 2.0 3.0 + - 3.0 8.0 2.0 / + * 2.0 ^ 3.0 +";
		TreeCalculator.evaluate(str3);
		test((TreeCalculator.getInfix().equals("( ( ( ( 6.0 - ( 2.0 + 3.0 ) ) * ( 3.0 + ( 8.0 / 2.0 ) ) ) ^ 2.0 ) + 3.0 )")) , "The answer should be: ( ( ( ( 6.0 - ( 2.0 + 3.0 ) ) * ( 3.0 + ( 8.0 / 2.0 ) ) ) ^ 2.0 ) + 3.0 )");
		test((TreeCalculator.getPostfix().equals("6.0 2.0 3.0 + - 3.0 8.0 2.0 / + * 2.0 ^ 3.0 +")) , "The answer should be: 6.0 2.0 3.0 + - 3.0 8.0 2.0 / + * 2.0 ^ 3.0 +");
		test((TreeCalculator.getPrefix().equals("+ ^ * - 6.0 + 2.0 3.0 + 3.0 / 8.0 2.0 2.0 3.0")) , "The answer should be: + ^ * - 6.0 + 2.0 3.0 + 3.0 / 8.0 2.0 2.0 3.0");
		test((TreeCalculator.evaluate(str3)) == 52 , "The answer should be: 52.0");
	}

	/**
	 * Checks the ExpTokenizer class.
	 */
	private static void testExpTokenizer(){
		String str1 = "( 7 + 3 ) * ( 18 - 2 )";
		ExpTokenizer tokenizer = new ExpTokenizer(str1);
		test((tokenizer.hasMoreElements()), "The answer should be: true");
		test((tokenizer.nextToken().equals("(")), "The answer should be (");
		test((tokenizer.countTokens() == 10), "The answer should be 10");

	}
		
}