package shadercat.automato;

public class Company {

    String name;
    String email;
    String compDescry;
    String number;
    String location;
    String createTime;

    public Company() {
        this.name = "";
        this.compDescry = "";
        this.email = "";
        this.createTime = "";
        this.number = "";
        this.location = "";
    }

    public Company(String name, String email, String compDescry, String number, String location, String createTime) {
        this.name = name;
        this.email = email;
        this.compDescry = compDescry;
        this.number = number;
        this.location = location;
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompDescry() {
        return compDescry;
    }

    public void setCompDescry(String compDescry) {
        this.compDescry = compDescry;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
