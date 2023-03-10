public class SubtractOp extends BinaryOp {

    @Override
    public double getPrecedence() {
        return 3;
    }

    @Override
    public double operate(double left, double right) {
        return left - right;
    }

    @Override
    public String toString() {
        return "-";
    }
}
