package Tdd.TddExample;

public class Money implements Expression {
    protected  int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount=amount;
        this.currency=currency;
    }

    protected String currency(){
        return this.currency;
    };

    @Override
    public Expression times(int multiplier){
        return new Money(this.amount*multiplier,this.currency);
    }

    @Override
    public Expression plus(Expression added){
        return new Sum(this,added);
    }

    public  static Money dollar(int amount){
        return new Money(amount,"USD");
    }

    public  static Money franc(int amount){
        return new Money(amount,"CHF");
    }

    @Override
    public Money reduce(Bank bank,String to){
        return new Money(amount/bank.rate(this.currency,to),to);
    }


    public  boolean equals(Object obj){
        Money money = (Money) obj;
        return  this.amount==money.amount &&this.currency.equals(money.currency);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }


}
