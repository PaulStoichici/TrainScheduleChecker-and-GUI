
import javax.swing.*;
class TrainScheduleGUI extends JFrame {
    private JLabel statusLabel;

    public TrainScheduleGUI() {
        // Initializam elementele GUI
        statusLabel = new JLabel("");
        add(statusLabel);

        // Proprietatile GUI
        setTitle("Train Schedule Checker");
        setSize(300, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void updateStatus(String status) {
        statusLabel.setText(status);
    }
}