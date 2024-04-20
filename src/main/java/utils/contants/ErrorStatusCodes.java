package utils.contants;


public interface ErrorStatusCodes {
    Integer InvalidPropertiesException = 400;
    Integer NotFoundException = 404;
    Integer DuplicateEntryException = 409;
    Integer TokenMissingException = 401;
    Integer
            InternalServerException = 500;
    Integer UnauthorizedException = 401;
    Integer UnexpectedException = 404;
    Integer CreateFailedException = 500;
    Integer
            UpdateFailedException = 500;
    Integer InvalidCredentialsException = 401;
    Integer RegistrationFailedException = 500;
    Integer
            InvalidEndpointException = 404;
    Integer TokenVerificationException = 401;
    Integer OTPGenerationException = 401;
    Integer
            OTPExpiredException = 401;
    Integer OTPVerificationException = 401;
    Integer ForeignKeyViolationException = 512;
    Integer
            UnimplementedException = 404;
    Integer HealthCheckFailedException = 503;
    Integer BadRequestException = 400;

}
