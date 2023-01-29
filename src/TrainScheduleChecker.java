import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TrainScheduleChecker {

    public static void main(String[] args) {
        // Initialize GUI
        TrainScheduleGUI gui = new TrainScheduleGUI();
        gui.setVisible(true);

        // Citim fisierele CSV
        try (BufferedReader br1 = new BufferedReader(new FileReader("src/CSV1.csv"));
             BufferedReader br2 = new BufferedReader(new FileReader("src/CSV2.csv"))) {
            String line1, line2;
            while ((line1 = br1.readLine()) != null && (line2 = br2.readLine()) != null) {
                String[] data1 = line1.split(",");
                String[] data2 = line2.split(",");

                //Se extrage numarul trenului, data si ora exacta din fisierele CSV
                String trainNumber = data1[2];
                LocalTime scheduledTime = LocalTime.parse(data1[1], DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime actualTime = LocalTime.parse(data2[1], DateTimeFormatter.ofPattern("HH:mm"));

                // Calculam delay-ul
                Duration delay = Duration.between(scheduledTime, actualTime);
                long delayMinutes = Math.abs(delay.toMinutes());

                // Determinam daca este intarziat/la timp/in avans
                String status;
                if (delay.isNegative()) {
                    status = "in advance";
                } else if (delay.isZero()) {
                    status = "on time";
                } else {
                    status = "delayed";
                }

                // Update GUI cu starea trenului si durata timpului
                gui.updateStatus("Train " + trainNumber + " is " + status + " with " + delayMinutes / 60 + " (hrs) and " + delayMinutes % 60 + " (min)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
