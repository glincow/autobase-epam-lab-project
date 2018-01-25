package model;

public class Ride {

    private Long id;
    private String name;
    private float mass;
    private float volume;
    private Status status;
    private Transport executor;
    private User manager;
    private User customer;

    public enum Status {
        UNASSIGNED,
        IN_PROCESS,
        FINISHED
    }

    public Ride(Long id, String name, float mass, float volume, Status status, User customer) {
        this.id = id;
        this.name = name;
        this.mass = mass;
        this.volume = volume;
        this.status = status;
        this.customer = customer;
    }

    public Ride() {
    }



    public Ride(Long id, String name, float mass, float volume, Status status, Transport executor, User manager, User customer) {
        this.id = id;
        this.name = name;
        this.mass = mass;
        this.volume = volume;
        this.status = status;
        this.executor = executor;
        this.manager = manager;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
