package cn.testFrame.jieshiqi;

public class Test {
    public static void main(String[] args) {
        TerminalExpression t1 = new TerminalExpression("张三");
        TerminalExpression t2 = new TerminalExpression("里斯");
        AndExpresion andExpresion = new AndExpresion(t1, t2);
        System.out.println(andExpresion.interper("张三"));
        OrExpression orExpression = new OrExpression(t1, t2);
        System.out.println(orExpression.interper("张三"));
    }
}
