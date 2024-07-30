interface Command {
    void execute();
}

class LightOnCommand implements Command {
    private Light targetLight;

    public LightOnCommand(Light targetLight) {
        this.targetLight = targetLight;
    }

    @Override
    public void execute() {
        targetLight.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light targetLight;

    public LightOffCommand(Light targetLight) {
        this.targetLight = targetLight;
    }

    @Override
    public void execute() {
        targetLight.turnOff();
    }
}

class Light {
    public void turnOn() {
        System.out.println("The light is on");
    }

    public void turnOff() {
        System.out.println("The light is off");
    }
}

class RemoteControl {
    private Command currentCommand;

    public void setCommand(Command command) {
        this.currentCommand = command;
    }

    public void pressButton() {
        currentCommand.execute();
    }
}

public class CommandPattern {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();

        Command lightOnCommand = new LightOnCommand(livingRoomLight);
        Command lightOffCommand = new LightOffCommand(livingRoomLight);

        RemoteControl remoteControl = new RemoteControl();

        remoteControl.setCommand(lightOnCommand);
        remoteControl.pressButton();

        remoteControl.setCommand(lightOffCommand);
        remoteControl.pressButton();
    }
}
