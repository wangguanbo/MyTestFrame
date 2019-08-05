package cn.testFrame.jieshiqi;

public class TerminalExpression implements Expression {

    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interper(String context) {
        if (context.contains(data)) {
            return true;
        }
        return false;
    }
}
