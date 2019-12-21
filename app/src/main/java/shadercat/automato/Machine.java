package shadercat.automato;

public class Machine {
    private String name = "";
    private String macId = "";
    private String prodState = "";
    private String state = "";
    private String id = "";

    public Machine() {

    }

    public Machine(String name, String macId, String prodState, String state, String id) {
        this.name = name;
        this.macId = macId;
        this.prodState = prodState;
        this.state = state;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getMacId() {
        return macId;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

    public void setProdState(String prodState) {
        this.prodState = prodState;
    }

    public String getProdState() {
        return prodState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
