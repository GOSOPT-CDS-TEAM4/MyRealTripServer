package sopt.org.MyRealTrip.exception.model;

public class NotFoundException extends SoptException {
    public NotFoundException(Error error, String message) {
        super(error, message);
    }
}