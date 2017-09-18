package engine;

public class Literal
{
    private char symbol_;
    private boolean isPositive_;

    public Literal( char theSymbol, boolean positive ){
        symbol_ = theSymbol;
        isPositive_= positive;
    }

    public char getSymbol() {
        return symbol_;
    }

    public boolean isPositive() {
        return isPositive_;
    }

    public void setPositive(boolean positive) {
        isPositive_ = positive;
    }

    public void setSymbol(char value) {
        this.symbol_ = value;
    }
}


