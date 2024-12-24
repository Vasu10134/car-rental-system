public class Car {
    private int carId;
    private String carModel;
    private String carType;
    private double pricePerDay;
    private String status;

    public Car(int carId, String carModel, String carType, double pricePerDay, String status) {
        this.carId = carId;
        this.carModel = carModel;
        this.carType = carType;
        this.pricePerDay = pricePerDay;
        this.status = status;
    }

    // Getters and setters for car details
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
