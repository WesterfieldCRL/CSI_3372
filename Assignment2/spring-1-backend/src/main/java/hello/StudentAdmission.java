package hello;

public class StudentAdmission {

    public enum Level {
        UNDERGRADUATE,
        MASTERS,
        PHD
    }

    public enum Status {
        ACCEPTED,
        DENIED
    }

    private String name;

    private String address;

    private String email;

    private String phone;

    private Level level;

    private Status status;

    public StudentAdmission() {
    }

    public StudentAdmission(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public StudentAdmission(String name, String address, String email, String phone, Level level, Status status) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.level = level;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}