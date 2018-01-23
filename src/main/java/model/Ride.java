package model;

public class Ride {

    private long id;
    private String name;
    private float mass;
    private float volume;
    private String status;
    private Transport executor;
    private User manager;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Transport getExecutor() {
        return executor;
    }

    public void setExecutor(Transport executor) {
        this.executor = executor;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Ride [id = " + id + ", name = " + name + ", paramMass = " + mass
                + ", paramVolume = " + volume + ", status = " + status
                + ", executorId = " + executor.toString() + ", managerId = " + manager.toString() + "]";
    }
}