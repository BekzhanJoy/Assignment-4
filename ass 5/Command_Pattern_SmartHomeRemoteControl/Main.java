
interface Command {
    void execute();
    void undo();
}

class TV {
    public void turnOn() {
        System.out.println("TV is turned ON");
    }

    public void turnOff() {
        System.out.println("TV is turned OFF");
    }
}

class Stereo {
    public void setVolume(int volume) {
        System.out.println("Stereo volume set to " + volume);
    }
}

class TurnTVOn implements Command {
    private TV tv;

    public TurnTVOn(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOn();
    }

    @Override
    public void undo() {
        tv.turnOff();
    }
}

class SetStereoVolume implements Command {
    private Stereo stereo;
    private int volume;

    public SetStereoVolume(Stereo stereo, int volume) {
        this.stereo = stereo;
        this.volume = volume;
    }

    @Override
    public void execute() {
        stereo.setVolume(volume);
    }

    @Override
    public void undo() {
        stereo.setVolume(0);
    }
}

class RemoteControl {
    private Command[] commands = new Command[5];
    private Command lastCommand;

    public void setCommand(int slot, Command command) {
        commands[slot] = command;
    }

    public void pressButton(int slot) {
        if (commands[slot] != null) {
            commands[slot].execute();
            lastCommand = commands[slot];
        }
    }

    public void pressUndo() {
        if (lastCommand != null) {
            lastCommand.undo();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();
        TV tv = new TV();
        Stereo stereo = new Stereo();

        remote.setCommand(0, new TurnTVOn(tv));
        remote.setCommand(1, new SetStereoVolume(stereo, 10));

        remote.pressButton(0);
        remote.pressButton(1);
        remote.pressUndo();
    }
}
