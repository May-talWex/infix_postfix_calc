public class StackCalculator extends Calculator {
    /**
     * this class extends the Calculator class
     * has two functions:
     * 1. infixToPostFix - converts infix expressions to postfix expressions
     * 2. evaluate - inherited from Calculator and evaluates a postfix expression and returns it's value
     */
    private StackAsArray stack;
    private ExpTokenizer tokenizer;



    public StackCalculator() {
        // constructor
        this.tokenizer = new ExpTokenizer("");
        this.stack = new StackAsArray();
    }


    public String infixToPostfix(String expr) {
        //function receives infix as String parameter and returns a postfix expression as a String
        this.tokenizer = new ExpTokenizer(expr); //converting expr from string to tokens
        String str = ""; //initializing new string
        this.stack = new StackAsArray();
        Object token;
        while (tokenizer.hasMoreElements()) { //do while there are tokens left
            token = tokenizer.nextElement();
            if (token instanceof OpenBracket) { //push token if token is an open bracket
                this.stack.push(token);
            } else if (token instanceof CloseBracket) { //do if token is a close bracket
                Object popped = null;
                while (!(this.stack.isEmpty()) && !(popped instanceof OpenBracket)) { // pop from stack and insert into str while the popped element isn't an open bracket
                    popped = this.stack.pop();
                    if (!(popped instanceof OpenBracket)){
                        str += popped + " ";
                        popped = null;
                    }
                }
            } else if (token instanceof BinaryOp) { //if token is operator amd the stack isn't empty, push token to stack
                if (this.stack.isEmpty()) {
                    this.stack.push(token);
                }else {
                    Object popped = this.stack.pop();
                    if (popped instanceof OpenBracket) {
                        this.stack.push(popped);
                    } else {
                        while (popped != null && ((BinaryOp) token).getPrecedence() > ((BinaryOp) popped).getPrecedence()) {//pop elements from the stack until operator with lower precedence or non-operator
                            if((popped instanceof PowOp) && token instanceof PowOp){
                                this.stack.push(popped);
                                popped = null;
                            }
                            else if(popped instanceof PowOp){
                                str += popped + " ";
                                popped = null;
                            }
                            else{
                                str += popped + " ";
                                if(!(this.stack.isEmpty())){
                                    popped = this.stack.pop();
                                }
                                else{
                                    popped = null;
                                }
                            }
                        }
                        if (popped != null){
                            this.stack.push(popped);
                        }
                    }
                    this.stack.push(token); // push token to stack
                }
            } else if (token instanceof ValueToken) { //if token is a number, concatenate to str
                str += token + " ";
            }
        }
        while (!this.stack.isEmpty()) { //while stack not empty pop each element and add to end of str
            if(this.stack.size > 1){
            str += this.stack.pop() + " ";}
            else{
                str += this.stack.pop();
            }
        }
        return str;
    }

    public double evaluate(String expr) {
        //function receives postfix expression as String and returns the calculation of the expression. Returns a double
        this.tokenizer = new ExpTokenizer(expr);
        this.stack = new StackAsArray();
        Object token;
        double left;
        double right;
        while (tokenizer.hasMoreElements()) { // while there are tokens left
            token = tokenizer.nextElement();
            if (token instanceof BinaryOp) { // if the token is an operator calculate the next two popped elements with the given operator and push to stack
                right = Double.parseDouble(stack.pop().toString());
                left = Double.parseDouble(stack.pop().toString());
                stack.push(((BinaryOp) token).operate(left, right));
            } else { //otherwise, (if token not operator) push the token into the stack
                stack.push(token);
            }
        }
        return (double) stack.pop();
    }


}

