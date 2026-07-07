package sst.bank.services;

public class CategoriesServiceException extends Exception{
    public CategoriesServiceException(String message) {
        super(message);
    }

    public CategoriesServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
