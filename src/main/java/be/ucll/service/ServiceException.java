package be.ucll.service;

public class ServiceException extends Exception {
    private String field;

    public ServiceException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
