public class ValueToken extends CalcToken {

    private double val;

    ValueToken(double val) {
        super();
        this.val = val;
    }

    public double getValue() {
        return this.val;
    }


    @Override
    public String toString() {
        return String.valueOf(this.val);
    }
}
