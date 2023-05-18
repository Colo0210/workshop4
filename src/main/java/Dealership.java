import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }
    public List<Vehicle> getVehiclesByYearRange(int minYear, int maxYear) {
        return inventory.stream()
                .filter(v -> v.getYear() >= minYear && v.getYear() <= maxYear)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByMileageRange(int startMileage, int endMileage) {
        return inventory.stream()
                .filter(v -> v.getMileage() >= startMileage && v.getMileage() <= endMileage)
                .collect(Collectors.toList());
    }
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public boolean removeVehicle(Vehicle vehicle) {
        return inventory.remove(vehicle);
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        return inventory.stream()
                .filter(v -> v.getPrice() >= min && v.getPrice() <= max)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return inventory.stream()
                .filter(v -> v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        return inventory.stream()
                .filter(v -> v.getYear() >= min && v.getYear() <= max)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return inventory.stream()
                .filter(v -> v.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        return inventory.stream()
                .filter(v -> v.getMileage() >= min && v.getMileage() <= max)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        return inventory.stream()
                .filter(v -> v.getVehicleType().equalsIgnoreCase(vehicleType))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }
    public void showVehicles() {
        if (inventory.size() == 0) {
            System.out.println("No vehicles currently in the dealership.");
            return;
        }

        for (Vehicle vehicle : inventory) {
            System.out.println(vehicle);
        }
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}