package cn.testFrame.jieshiqi;

public class OrExpression implements Expression {

    private Expression expression1;
    private Expression expression2;

    public OrExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public boolean interper(String context) {
        return expression1.interper(context) || expression2.interper(context);
    }
}
