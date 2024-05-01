package utils.exceptions.api;

import utils.contants.ErrorCodes;
import utils.contants.ErrorStatusCodes;
import utils.exceptions.Exception;
import utils.response.MessageResponse;

public class MethodNotAllowed extends Exception {
    public MethodNotAllowed() {
        this.errorCode = ErrorCodes.InvalidEndpointException;
        this.statusCode = ErrorStatusCodes.;
        this.message = "Method not allowed";
    }
}
