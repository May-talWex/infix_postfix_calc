public class DivideOp extends BinaryOp {
    private char divideOp;

    public DivideOp() {
        super();
        this.divideOp = '^';
    }

    @Override
    public double getPrecedence() {
        return 2;
    }

    @Override
    public double operate(double left, double right) {
        return left / right;
    }

    @Override
    public String toString() {
        return "/";
    }
}
