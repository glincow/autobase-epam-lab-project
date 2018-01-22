package model;

public class Transport {

    private int id;
    private float maxMass;
    private float maxVolume;
    private boolean isAutoWorks;
    private boolean isAutoAvailable;
    private int driverId;

    public int getId() {
        return id;
    }

    public float getMaxMass() {
        return maxMass;
    }

    public void setMaxMass(float maxMass) {
        this.maxMass = maxMass;
    }

    public float getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(float maxVolume) {
        this.maxVolume = maxVolume;
    }

    public boolean getIsAutoWorks() {
        return isAutoWorks;
    }

    public void setIsAutoWorks(boolean isAutoWorks) {
        this.isAutoWorks = isAutoWorks;
    }

    public boolean getIsAutoAvailable() {
        return isAutoAvailable;
    }

    public void setIsAutoAvailable(boolean isAutoAvailable) {
        this.isAutoAvailable = isAutoAvailable;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return "Transport [id = " + id + ", maxMass = " + maxMass + ", maxVolume = " + maxVolume +
                ", isAutoWorks = " + isAutoWorks + ", isAutoAvailable = " + isAutoAvailable + ", driverId = " + driverId + "]";
    }
}
