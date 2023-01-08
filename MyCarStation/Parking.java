package MyCarStation;

import java.util.ArrayList;
import java.util.List;

public class Parking {

    // The counter of free parks
    private int freeParks;
    private List<Thread> parkedCars = new ArrayList<>();

    Parking(int freeParks) {
        this.freeParks = freeParks;
    }

    // If there is a free space, the car takes it
    // if there is no, the car waits the limited time, and then leaves the parking
    public synchronized boolean accept(Car car) throws InterruptedException {
        while (freeParks == 0) {
            wait(car.getWaitTime());
            if (freeParks == 0) return false;
            else break;
        }
        freeParks--;
        parkedCars.add(car);
        return true;
    }

    // When the parking time is over, the car leaves and notifies others, so they can take place
    public synchronized void release(Car car) {
        if (parkedCars.contains(car)) {
            freeParks++;
            parkedCars.remove(car);
            notifyAll();
        }
    }
}
