package Tdd.TddExample;

public class Sum implements  Expression{
    Expression augmend;
    Expression addmend;

    @Override
    public Money reduce(Bank bank,String to){
        int amount =this.augmend.reduce(bank,to).amount +this.addmend.reduce(bank,to).amount;
        return  new Money(amount,to);
    }

    @Override
    public Expression times(int multiplier) {
        return new Sum(augmend.times(multiplier),addmend.times(multiplier));
    }

    @Override
    public Expression plus(Expression added) {
        return new Sum(this,addmend);
    }

    public  Sum(Expression augmend,Expression addmend){
        this.augmend=augmend;
        this.addmend=addmend;
    }

}
