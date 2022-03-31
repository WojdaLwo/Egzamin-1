package tanks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Operation {

    private final LocalDateTime dateAndTime;
    private final String nameOfOperation;
    private final Tank tank;
    private final double amountOfWater;
    private final boolean isSuccess;

    private static List<Operation> extension = new ArrayList<>();


    public Operation(String nameOfOperation, Tank tank, double amountOfWater, boolean isSuccess) {
        this.dateAndTime = LocalDateTime.now();
        this.nameOfOperation = nameOfOperation;
        this.tank = tank;
        this.amountOfWater = amountOfWater;
        this.isSuccess = isSuccess;

        extension.add(this);
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public String getNameOfOperation() {
        return nameOfOperation;
    }

    public Tank getTank() {
        return tank;
    }

    public double getAmountOfWater() {
        return amountOfWater;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public static List<Operation> getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return nameOfOperation + " " + tank + " isSuccess=" + isSuccess;
    }
}
