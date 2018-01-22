package model;

public class Ride {

    private int id;
    private String name;
    private float paramMass;
    private float paramVolume;
    private String status;
    private int executorId;
    private int managerId;

    public int getId() {
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

    public int getExecutor() {
        return executorId;
    }

    public void setExecutor(int executorId) {
        this.executorId = executorId;
    }

    public int getManager() {
        return managerId;
    }

    public void setManager(int managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "Ride [id = " + id + ", name = " + name + ", paramMass = " + paramMass
                + ", paramVolume = " + paramVolume + ", status = " + status
                + ", executorId = " + executorId + ", managerId = " + managerId + "]";
    }
}