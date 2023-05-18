import java.io.*;

public class DealershipFileManager {
    private String filePath;
    private String tempFilePath;

    public DealershipFileManager(String filePath) {
        this.filePath = filePath;
        this.tempFilePath = "temp_" + filePath;
    }

    public Dealership getDealership() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String dealershipInfo = reader.readLine();
            String[] dealershipDetails = dealershipInfo.split("\\|");

            Dealership dealership = new Dealership(
                    dealershipDetails[0],
                    dealershipDetails[1],
                    dealershipDetails[2]
            );

            String vehicleInfo;
            while ((vehicleInfo = reader.readLine()) != null) {
                String[] vehicleDetails = vehicleInfo.split("\\|");

                String id = vehicleDetails[0];
                String make = vehicleDetails[1];
                String model = vehicleDetails[2];
                int year = Integer.parseInt(vehicleDetails[3]);
                double price = Double.parseDouble(vehicleDetails[4]);
                String color = vehicleDetails[5];
                int mileage = Integer.parseInt(vehicleDetails[6]);
                String vehicleType = vehicleDetails[7];

                Vehicle vehicle = new Vehicle(id, make, model, year, price, color, mileage, vehicleType);
                dealership.addVehicle(vehicle);
            }
            return dealership;
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IOException("Error reading from file: " + filePath, e);
        }
    }

    public void saveDealership(Dealership dealership) throws IOException {
        File file = new File(filePath);
        File tempFile = new File(tempFilePath);

        if (!file.exists()) {
            file.createNewFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            writer.newLine();

            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writer.write(vehicleToString(vehicle));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IOException("Error writing to temp file: " + tempFilePath, e);
        }

        if (!file.delete()) {
            throw new IOException("Could not delete original file");
        }

        if (!tempFile.renameTo(file)) {
            throw new IOException("Could not rename temp file");
        }
    }

    private String vehicleToString(Vehicle vehicle) {
        return vehicle.getId() + "|" +
                vehicle.getMake() + "|" +
                vehicle.getModel() + "|" +
                vehicle.getYear() + "|" +
                vehicle.getPrice() + "|" +
                vehicle.getColor() + "|" +
                vehicle.getMileage() + "|" +
                vehicle.getVehicleType();
    }
}