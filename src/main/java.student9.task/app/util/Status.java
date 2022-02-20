package app.util;

public enum Status {
    GOOD("good"),
    DELETED("deleted");

    public final String status;

    Status(String status) {
        this.status = status;
    }
}