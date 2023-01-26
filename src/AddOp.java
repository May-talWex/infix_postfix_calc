public class AddOp extends BinaryOp {
    private char addOp;

    public AddOp() {
        super();
        this.addOp = '^';
    }

    @Override
    public String toString() {
        return "+";
    }

    @Override
    public double getPrecedence() {
        return 3;
    }

    @Override
    public double operate(double left, double right) {

        return left + right;
    }
}

