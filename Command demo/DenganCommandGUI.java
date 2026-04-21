import javax.swing.*;
import java.awt.*;
import java.util.Stack;

// Command interface
interface Command {
    void execute();
    void undo();
}

// Receiver
class Lampu {
    public void nyala() {
        System.out.println("Lampu dinyalakan");
    }
    public void mati() {
        System.out.println("Lampu dimatikan");
    }
}

// Concrete Command ON
class LampuOnCommand implements Command {
    private Lampu lampu;

    public LampuOnCommand(Lampu lampu) {
        this.lampu = lampu;
    }

    public void execute() { lampu.nyala(); }
    public void undo()    { lampu.mati(); }  // kebalikannya
}

// Concrete Command OFF
class LampuOffCommand implements Command {
    private Lampu lampu;

    public LampuOffCommand(Lampu lampu) {
        this.lampu = lampu;
    }

    public void execute() { lampu.mati(); }
    public void undo()    { lampu.nyala(); } // kebalikannya!
}

// Invoker 
class RemoteControl {
    private Stack<Command> history = new Stack<>();

    public void execute(Command cmd) {
        cmd.execute();
        history.push(cmd); // simpan ke history
    }

    public void undo() {
        if (!history.isEmpty()) {
            Command cmd = history.pop(); // ambil command terakhir
            cmd.undo();                  // balik perintahnya
        } else {
            System.out.println("Tidak ada yang bisa di-undo!");
        }
    }
}

public class DenganCommandGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Command + Undo");
        JButton btnOn   = new JButton("ON");
        JButton btnOff  = new JButton("OFF");
        JButton btnUndo = new JButton("UNDO");

        frame.setLayout(new FlowLayout());

        Lampu lampu          = new Lampu();
        RemoteControl remote = new RemoteControl();

        Command onCommand  = new LampuOnCommand(lampu);
        Command offCommand = new LampuOffCommand(lampu);

        btnOn.addActionListener(e  -> remote.execute(onCommand));
        btnOff.addActionListener(e -> remote.execute(offCommand));
        btnUndo.addActionListener(e -> remote.undo());

        frame.add(btnOn);
        frame.add(btnOff);
        frame.add(btnUndo);

        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}