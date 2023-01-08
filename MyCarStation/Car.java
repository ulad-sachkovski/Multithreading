package MyCarStation;

public class Car extends Thread {

    private String carName;
    private int arriveTime;
    private int parkingTime;
    private int waitTime;
    private Parking parking;
    private static int carQuantity;

    Car(String carName, int arriveTime, int parkingTime, int waitTime, Parking parking) {
        this.carName = carName;
        this.arriveTime = arriveTime;
        this.parkingTime = parkingTime;
        this.waitTime = waitTime;
        this.parking = parking;
    }

    // Getter of waiting time
    public int getWaitTime() {
        return waitTime;
    }

    // The car is inactive until arrival time
    // When it's time, the car is looking for free parking space
    // If there is a free space, the car is inactive until arrival time and then releases parking space
    // If there is no free place, the car waits for it (see parking methods). If the waiting time is over, the car leaves
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() +
                    ": arriving time: " + arriveTime +
                    ": parking time: " + parkingTime +
                    ": waiting time: " + waitTime);
            Thread.sleep(arriveTime);
            boolean isParked = parking.accept(this);
            if (isParked) {
                Thread.sleep(parkingTime);
                parking.release(this);
            } else {
                System.out.println(Thread.currentThread().getName() + " can not wait more and is leaving");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
