import java.util.Scanner;

class GeyserSystem {

    // Instance variables (state of the geyser)
    int currentTemperature = 25;
    int roomTemperature = 30;
    int targetTemperature;
    int maxHeatingTime;
    int maxSafeTemperature;
    int elapsedTime = 0;
    boolean geyserOn = true;
    String shutdownReason = "";

    // Method to set mode parameters
    void setMode(int mode) {
        switch (mode) {
            case 1:
                targetTemperature = 40;
                maxHeatingTime = 10;
                maxSafeTemperature = 50;
                break;

            case 2:
                targetTemperature = 50;
                maxHeatingTime = 15;
                maxSafeTemperature = 60;
                break;

            case 3:
                targetTemperature = 60;
                maxHeatingTime = 20;
                maxSafeTemperature = 70;
                break;

            default:
                targetTemperature = 40;
                maxHeatingTime = 10;
                maxSafeTemperature = 50;
        }
    }

    // Method to heat water
    void heatWater() {
        if (roomTemperature < 20) {
            currentTemperature += 1;
        } else {
            currentTemperature += 2;
        }
        elapsedTime++;
    }

    // Method to check shutdown conditions
    void checkShutdown() {
        if (currentTemperature >= targetTemperature) {
            shutdownReason = "Target temperature reached";
            geyserOn = false;
        } else if (elapsedTime >= maxHeatingTime) {
            shutdownReason = "Maximum heating time exceeded";
            geyserOn = false;
        } else if (currentTemperature >= maxSafeTemperature) {
            shutdownReason = "Overheating detected (Emergency shutdown)";
            geyserOn = false;
        }
    }
}

public class Geyser {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        GeyserSystem geyser = new GeyserSystem();

        System.out.println("Enter numbers from 1-3");
        System.out.println("1. Normal Hot Water");
        System.out.println("2. Medium Hot Water");
        System.out.println("3. Very Hot Water");

        int mode = s.nextInt();
        geyser.setMode(mode);

        System.out.println("Do you want to enter the room temperature?");
        System.out.println("Click Y for Yes and N for No");
        char choice = s.next().charAt(0);

        if (choice == 'y' || choice == 'Y') {
            System.out.println("Enter room temperature in °Celsius");
            int temp = s.nextInt();

            if (temp >= 0 && temp <= 60) {
                geyser.roomTemperature = temp;
            }
        }

        System.out.println("Room Temperature     : " + geyser.roomTemperature + "°C");

        // Heating process
        while (geyser.geyserOn) {

            geyser.heatWater();

            System.out.println("Time: " + geyser.elapsedTime +
                    " min | Temperature: " + geyser.currentTemperature + "°C");

            geyser.checkShutdown();
        }

        // Summary
        System.out.println("\n----- Geyser Shutdown Summary -----");
        System.out.println("Final Temperature   : " + geyser.currentTemperature + "°C");
        System.out.println("Total Heating Time  : " + geyser.elapsedTime + " minutes");
        System.out.println("Shutdown Reason     : " + geyser.shutdownReason);
        System.out.println("Geyser Status       : OFF");
        System.out.println("----------------------------------");
        System.out.println("Thank you. System exited safely.");

        s.close();
    }
}
