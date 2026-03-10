import java.util.*;

class Geyser {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int targetTemperature;
        int maxHeatingTime;
        int maxSafeTemperature;
        int roomTemperature=30;
        String modeName;
        String shutdownReason = "";

        System.out.println("Enter numbers from 1-3");
        System.out.println("1. Normal Hot Water");
        System.out.println("2. Medium Hot Water");
        System.out.println("3. Very Hot Water");

        int n = s.nextInt();
        
        System.out.println("Do you want to enter the room temperature?");
        System.out.println("Click Y for Yes and N for No");
        char choice=s.next().charAt(0);
        
        if(choice=='y'||choice=='Y'){
            System.out.println("Enter room temperature in °Celsius");
            roomTemperature=s.nextInt();
            if (roomTemperature < 0 || roomTemperature > 60) {
                    roomTemperature = 30;
                }
            System.out.println("Room Temperature     : " + roomTemperature + "°C");
        }
        
        switch (n) {
            case 1:
                modeName = "Normal Hot Water";
                targetTemperature = 40;
                maxHeatingTime = 10;
                maxSafeTemperature = 50;
                break;

            case 2:
                modeName = "Medium Hot Water";
                targetTemperature = 50;
                maxHeatingTime = 15;
                maxSafeTemperature = 60;
                break;

            case 3:
                modeName = "Very Hot Water";
                targetTemperature = 60;
                maxHeatingTime = 20;
                maxSafeTemperature = 70;
                break;

            default:
                modeName = "Normal Hot Water (Default)";
                targetTemperature = 40;
                maxHeatingTime = 10;
                maxSafeTemperature = 50;
        }
        //currentTemperature is initial water temperature
        //elapsedTime is the time until which the geyser is on
        int currentTemperature = 25;
        int elapsedTime = 0;
        boolean geyserOn = true;

        while (geyserOn) {
            
            if(roomTemperature<20){
                currentTemperature+=1;
            }
            else{
                currentTemperature+=2;
            }
            
            elapsedTime++;
            
            System.out.println("Time: " + elapsedTime + 
                               " min | Temperature: " + currentTemperature + "°C");

            if (currentTemperature >= targetTemperature) {
                shutdownReason = "Target temperature reached";
                geyserOn = false;
            }
            else if (elapsedTime >= maxHeatingTime) {
                shutdownReason = "Maximum heating time exceeded";
                geyserOn = false;
            }
            else if (currentTemperature >= maxSafeTemperature) {
                shutdownReason = "Overheating detected (Emergency shutdown)";
                geyserOn = false;
            }
        }

        System.out.println("\n----- Geyser Shutdown Summary -----");
        System.out.println("Selected Mode       : " + modeName);
        System.out.println("Final Temperature   : " + currentTemperature + "°C");
        System.out.println("Total Heating Time  : " + elapsedTime + " minutes");
        System.out.println("Shutdown Reason     : " + shutdownReason);
        System.out.println("Geyser Status       : OFF");
        System.out.println("----------------------------------");
        System.out.println("Thank you. System exited safely.");

        s.close();
    }
}
