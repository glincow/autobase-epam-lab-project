package model;

public class Transport {

    private long id;
    private float maxMass;
    private float maxVolume;
    private boolean isAutoWorks;
    private boolean isAutoAvailable;
    private User driver;

    public long getId() {
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

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Transport [id = " + id + ", maxMass = " + maxMass + ", maxVolume = " + maxVolume +
                ", isAutoWorks = " + isAutoWorks + ", isAutoAvailable = " + isAutoAvailable + ", driver = " + driver.toString() + "]";
    }
}
