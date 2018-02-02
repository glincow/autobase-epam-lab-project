package model;

import java.util.Objects;

public class Transport {

    private Long id;
    private float maxMass;
    private float maxVolume;
    private boolean isAutoWorks;
    private boolean isAutoAvailable;
    private User driver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return Float.compare(transport.maxMass, maxMass) == 0 &&
                Float.compare(transport.maxVolume, maxVolume) == 0 &&
                isAutoWorks == transport.isAutoWorks &&
                isAutoAvailable == transport.isAutoAvailable &&
                Objects.equals(id, transport.id) &&
                Objects.equals(driver, transport.driver);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, maxMass, maxVolume, isAutoWorks, isAutoAvailable, driver);
    }
}
