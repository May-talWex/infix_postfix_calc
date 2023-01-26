public class ExpressionTree {
    private TreeNode root;

    public ExpressionTree(String postFix){
        BuildExpressionTree(postFix);
    }


    void BuildExpressionTree(String postfixExp) { // function to build a tree
        StackAsArray stack = new StackAsArray(); //creating empty stack
        ExpTokenizer tokenizer = new ExpTokenizer(postfixExp); //converting the given postfix expression to array of tokens
        Object token;
        TreeNode node = new TreeNode(null);
        while (tokenizer.hasMoreElements()) { //while tokenizer isn't empty
            token = tokenizer.nextElement(); //assigning to the token object
            if (token instanceof ValueToken) { //if the token is a number value create a node containing the token and push the node to the stack
                node = new TreeNode(token);
                stack.push(node);
            } else if (token instanceof BinaryOp) { //else if the token is an operator pop two elements from the stack and initiate each of them to the right and left children of the node
                TreeNode right = (TreeNode) stack.pop();
                node = new TreeNode(token, (TreeNode) stack.pop(), right);
                stack.push(node);
            }
        }
        this.root = (TreeNode) stack.pop();
    }

    public TreeNode getRoot(){
        //function to get the tree root
        return new TreeNode(this.root.data , this.root.left , this.root.right );
    }
}
