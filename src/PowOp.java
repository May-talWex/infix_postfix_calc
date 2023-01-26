public class PowOp extends BinaryOp {

    private char powOp;

    public PowOp() {
        super();
        this.powOp = '^';
    }

    @Override
    public double getPrecedence() {
        return 1;
    }

    @Override
    public double operate(double left, double right) {
        return Math.pow(left, right);
    }

    @Override
    public String toString() {
        return "^";
    }

}

