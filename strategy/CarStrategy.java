package strategy;

interface BrakeBehavior {
    void stop();
}

class ShortWheelBrake implements BrakeBehavior {
    @Override
    public void stop() {
        System.out.println("Simulate short tire brake marks!");
    }
}

class LongWheelBrake implements BrakeBehavior {
    @Override
    public void stop() {
        System.out.println("Simulate long tire brake marks!");
    }
}

class Car {
    protected BrakeBehavior behavior;

    public Car(BrakeBehavior behavior) {
        this.behavior = behavior;
    }

    public void brake() {
        this.behavior.stop();
    }
}

class ShortWheelCar extends Car {
    public ShortWheelCar(BrakeBehavior behavior) {
        super(behavior);
    }
}

class LongWheelCar extends Car {
    public LongWheelCar(BrakeBehavior behavior) {
        super(behavior);
    }
}


public class CarStrategy {
    public static void main(String[] args) {
        BrakeBehavior brake1 = new ShortWheelBrake();
        BrakeBehavior brake2 = new LongWheelBrake();

        Car car = new ShortWheelCar(brake1);
        car.brake();

        car = new LongWheelCar(brake2);
        car.brake();
    }
}
