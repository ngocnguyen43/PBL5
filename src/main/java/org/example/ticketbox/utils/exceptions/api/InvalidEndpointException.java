package org.example.ticketbox.utils.exceptions.api;

import org.example.ticketbox.utils.enums.ErrorCodes;
import org.example.ticketbox.utils.enums.ErrorStatusCodes;
import org.example.ticketbox.utils.exceptions.Exception;

public class InvalidEndpointException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidEndpointException() {
        this.errorCode = ErrorCodes.InvalidEndpointException.getValue();
        this.statusCode = ErrorStatusCodes.InvalidEndpointException.getValue();
        this.message = "Not Found";
    }
}
