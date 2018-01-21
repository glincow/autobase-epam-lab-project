package model;

public class Transport {

    private int id;
    private float paramMaxMass;
    private float paramMaxVolume;
    private boolean autoWorks;
    private boolean autoAvailable;
    private int driver;

    public int getId() {
        return id;
    }

    public float getParamMaxMass() {
        return paramMaxMass;
    }

    public void setParamMaxMass(float paramMaxMass) {
        this.paramMaxMass = paramMaxMass;
    }

    public float getParamMaxVolume() {
        return paramMaxVolume;
    }

    public void setParamMaxVolume(float paramMaxVolume) {
        this.paramMaxVolume = paramMaxVolume;
    }

    public boolean isAutoWorks() {
        return autoWorks;
    }

    public void setAutoWorks(boolean autoWorks) {
        this.autoWorks = autoWorks;
    }

    public boolean isAutoAvailable() {
        return autoAvailable;
    }

    public void setAutoAvailable(boolean autoAvailable) {
        this.autoAvailable = autoAvailable;
    }

    public int getDriver() {
        return driver;
    }

    public void setDriver(int driver) {
        this.driver = driver;
    }
}
