package model;

public class Order {

    private int id;
    private String name;
    private float paramMass;
    private float paramVolume;
    private String status;
    private int executor;
    private int manager;

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
        return executor;
    }

    public void setExecutor(int executor) {
        this.executor = executor;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Order [id = " + id + ", name = " + name + ", paramMass = " + paramMass +
                ", paramVolume = " + paramVolume + ", status = " + status + ", executor = " + executor + ", manager = " + manager + "]";
    }
}