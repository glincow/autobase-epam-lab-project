package model;

public class Ride {

    private long id;
    private String name;
    private float paramMass;
    private float paramVolume;
    private String status;
    private Transport executor;
    private User manager;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getParamMass() {
        return paramMass;
    }

    public void setParamMass(float paramMass) {
        this.paramMass = paramMass;
    }

    public float getParamVolume() {
        return paramVolume;
    }

    public void setParamVolume(float paramVolume) {
        this.paramVolume = paramVolume;
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
        return "Ride [id = " + id + ", name = " + name + ", paramMass = " + paramMass
                + ", paramVolume = " + paramVolume + ", status = " + status
                + ", executorId = " + executor.toString() + ", managerId = " + manager.toString() + "]";
    }
}