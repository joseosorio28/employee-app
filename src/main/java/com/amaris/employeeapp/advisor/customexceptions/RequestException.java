package com.amaris.employeeapp.advisor.customexceptions;

public class RequestException extends RuntimeException {
    public RequestException() {
        super("Client is making to many consecutive request.");
    }
}
