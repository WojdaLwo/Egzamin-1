package tanks;

import java.util.ArrayList;
import java.util.List;

public class Tank {

    private final String name;
    private final double capacity;
    private double amountOfWater = 0.0;

    private List<Operation> operations = new ArrayList<>();

    private static List<Tank> extension = new ArrayList<>();

    public Tank(String name, double capacity) {
        this.name = name;
        this.capacity = capacity;

        extension.add(this);
    }

    public void addingWater(double howMuch) {
        boolean isSuccess;
        if (howMuch + amountOfWater <= capacity) {
            amountOfWater += howMuch;
            isSuccess = true;
        } else {
            System.out.println("Operation can't be done.");
            isSuccess = false;
        }
        Operation add = new Operation("adding water to", this, howMuch, isSuccess);
        operations.add(add);
    }

    public void removingWater(double howMuch) {
        boolean isSuccess;
        if (amountOfWater >= howMuch) {
            amountOfWater -= howMuch;
            isSuccess = true;
        } else {
            System.out.println("Operation can't be done.");
            isSuccess = false;
        }
        Operation remove = new Operation("removing water from", this, howMuch, isSuccess);
        operations.add(remove);
    }

    public void transferWaterTo(Tank to, double howMuch) {
        if (to == null) {
            throw new IllegalArgumentException("There is no " + to + " tank.");
        }
        boolean isSuccess;
        if (howMuch + to.amountOfWater <= to.capacity && amountOfWater >= howMuch) {
            to.amountOfWater += howMuch;
            amountOfWater -= howMuch;
            isSuccess = true;
        } else {
            System.out.println("Operation can't be done.");
            isSuccess = false;
        }
        Operation from = new Operation("transfer water from", this, howMuch, isSuccess);
        Operation into = new Operation("transfer water to", to, howMuch, isSuccess);
        operations.add(from);
        to.operations.add(into);
    }

    public static Tank mostWater(List<Tank> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List of tanks is empty, or doesn't exist.");
        }
        Tank max = list.get(0);
        for (Tank tank : list) {
            if (tank.amountOfWater > max.amountOfWater) {
                max = tank;
            }
        }
        return max;
    }

    public double filling() {
        return amountOfWater / capacity;
    }

    public static Tank largestFilling(List<Tank> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List of tanks is empty, or doesn't exist.");
        }
        Tank max = list.get(0);
        for (Tank tank : list) {
            if (tank.filling() > max.filling()) {
                max = tank;
            }
        }
        return max;
    }

    public static List<Tank> emptyTanks(List<Tank> list) {
        if (list == null) {
            throw new IllegalArgumentException("List of tanks doesn't exist.");
        }
        List<Tank> emptyTanks = new ArrayList<>();
        for (Tank tank : list) {
            if (tank.amountOfWater == 0) {
                emptyTanks.add(tank);
            }
        }
        return emptyTanks;
    }

    public int amountOfFailingOperations() {
        int i = 0;
        for (Operation operation : operations) {
            if (!operation.isSuccess()) {
                i++;
            }
        }
        return i;
    }

    public static Tank largestNumberOfFailingOperations(List<Tank> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List of tanks is empty, or doesn't exist.");
        }
        Tank max = list.get(0);
        int number = 0;
        for (Tank tank : list) {
            if (tank.amountOfFailingOperations() > number) {
                max = tank;
                number = tank.amountOfFailingOperations();
            }
        }
        return max;
    }

    public int amountOfOperationsOfType(String type) {
        int i = 0;
        for (Operation operation : operations) {
            if (operation.getNameOfOperation().equals(type)) {
                i++;
            }
        }
        return i;
    }

    public static Tank largestNumberOfOperationsOfType(List<Tank> list, String type) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List of tanks is empty, or doesn't exist.");
        }
        Tank max = list.get(0);
        int number = 0;
        for (Tank tank : list) {
            if (tank.amountOfOperationsOfType(type) > number) {
                max = tank;
                number = tank.amountOfFailingOperations();
            }
        }
        return max;
    }

    public boolean checkTheState() {
        double state = 0.0;
        for (Operation operation : operations) {
            if (operation.isSuccess()) {
                if (operation.getNameOfOperation().equals("adding water to") || operation.getNameOfOperation().equals("transfer water to")) {
                    state += operation.getAmountOfWater();
                } else {
                    state -= operation.getAmountOfWater();
                }
                if (state < 0 || state > capacity) {
                    throw new IllegalArgumentException("Amount of water was on illegal level.");
                }
            }
        }
        return state == amountOfWater;
    }

    public String getName() {
        return name;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getAmountOfWater() {
        return amountOfWater;
    }

    public static List<Tank> getExtension() {
        return extension;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    @Override
    public String toString() {
        return "Tank " + name;
    }
}
