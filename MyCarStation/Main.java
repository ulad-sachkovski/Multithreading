package MyCarStation;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int carQuantity = 5;
        int parkingSize = 2;
        Parking parking = new Parking(parkingSize);

        List<Thread> carList = createCars(carQuantity, parking);
        for (Thread thread : carList) {
            thread.start();
        }
    }

    // Function to create Threads of cars with parameters: arriving time, waiting time, parking time
    private static List<Thread> createCars(int carQuantity, Parking parking) {
        List<Thread> carList = new ArrayList<>();
        for (int i = 1; i <= carQuantity; i++) {
            int arriveTime = (int) (Math.random() * 1000);
            int parkingTime = (int) (Math.random() * 3000);
            int waitTime = (int) (Math.random() * 2000);
            Thread thread = new Thread(new Car("Car " + i, arriveTime, parkingTime, waitTime, parking));
            thread.setName("Car " + i);
            carList.add(thread);
        }
        return carList;
    }
}
