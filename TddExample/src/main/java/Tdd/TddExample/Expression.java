package Tdd.TddExample;

public interface Expression {

    public Money reduce(Bank bank, String toCurrency);

    Expression times(int multiplier);

    public Expression plus(Expression added);

}
