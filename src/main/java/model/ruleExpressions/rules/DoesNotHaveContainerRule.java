package model.ruleExpressions.rules;

public class DoesNotHaveContainerRule extends HasContainerRule {

    public DoesNotHaveContainerRule() {
        super();
    }

    @Override
    public Boolean validate() {
        return !super.interpret();
    }

}
