public class Literal
{
    private char value_;
    private boolean isPositive_;

    public Literal( char theChar, boolean positive ){
        value_ = theChar;
        isPositive_= positive;
    }

    public char getValue() {
        return value_;
    }

    public boolean isPositive() {
        return isPositive_;
    }

    public void setPositive_(boolean positive) {
        isPositive_ = positive;
    }

    public void setValue_(char value) {
        this.value_ = value;
    }
}
