package cn.testFrame.design.command;

public class Invoker  {

    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand(){
        command.execute();
    }

    public void unExecuteCommand(){
        command.unDo();
    }

}
