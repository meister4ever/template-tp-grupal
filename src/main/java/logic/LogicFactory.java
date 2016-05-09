package logic;

import model.ruleExpressions.expressions.IExpression;
import model.ruleExpressions.expressions.LogicExpression;

public abstract class LogicFactory {

    protected char symbol;

    public LogicFactory(){}

    public LogicExpression build(IExpression leftExp, IExpression rightExp, char symbol) {
        if (validExpression(symbol)) {
            LogicExpression result = build();
            result.setLeftExpression(leftExp);
            result.setRightExpression(rightExp);
            return result;
        }
        return null;
    }

    protected boolean validExpression(char symbol) {
        return symbol == this.symbol;
    }

    abstract LogicExpression build();

}