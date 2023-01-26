public class MultiplyOp extends BinaryOp {
    private char multiplyOp;

    public MultiplyOp() {
        super();
        this.multiplyOp = '^';
    }

    @Override
    public double getPrecedence() {
        return 2;
    }

    @Override
    public double operate(double left, double right) {
        return left * right;
    }

    @Override
    public String toString() {
        return "*";
    }
}
