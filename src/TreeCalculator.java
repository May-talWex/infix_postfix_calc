public class TreeCalculator extends Calculator {
    private ExpressionTree tree;

    public TreeCalculator() {
        this.tree = null;
    }

    // public function to get infix expression
    public String getInfix() { return getInfix(this.tree.getRoot());}

    // public function to get postfix expression
    public String getPostfix() { // public function to get postfix expression
        return getPostfix(this.tree.getRoot());
    }

    // public function to get prefix expression
    public String getPrefix() { // public function to get prefix expression
        return prefixCalc(this.tree.getRoot());
    }

    private String getInfix(TreeNode node) { //getInfix private recursive function
        if (node == null) {
            return "";
        } else if (node.isLeaf()) {
            return node.getData().toString();
        }
        return "( " + getInfix(node.getLeft()) + " " + node.getData().toString() + " " + getInfix(node.getRight()) + " )";
    }


    private String getPostfix(TreeNode node) { //getPostfix private recursive function
        if (node == null) {
            return "";
        } else if (node.isLeaf()) {
            return node.getData().toString();
        }
        return getPostfix(node.getLeft()) + " " + getPostfix(node.getRight()) + " " + node.getData().toString();
    }

    private String prefixCalc(TreeNode node) { //getPrefix private recursive function
        if (node == null) {
            return "";
        } else if (node.isLeaf()) {
            return node.getData().toString();
        }
        return node.getData().toString() + " " + prefixCalc(node.getLeft()) + " " + prefixCalc(node.getRight());
    }

    public double evaluateExpression(TreeNode node) { // recursive function that evaluates the expression using a tree
        if (node == null) {
            return 0;
        } else if (node.isLeaf()) {
            return ((ValueToken) node.getData()).getValue();
        }
        return ((BinaryOp) node.getData()).operate(evaluateExpression(node.getLeft()), evaluateExpression(node.getRight()));
    }

    @Override
    public double evaluate(String expr) { //returns the calculation of the expression as a string by calling the evaluateExpression function
        this.tree = new ExpressionTree(expr);
        return evaluateExpression(this.tree.getRoot());
    }
}
