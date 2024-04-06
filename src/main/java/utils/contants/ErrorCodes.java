package utils.contants;

public interface ErrorCodes {
    Integer InvalidCredentialsException = 1;
    Integer TokenMissingException = 2;
    Integer TokenVerificationException = 2;
    Integer TokenExpiredException = 3;

    Integer UnauthorizedException = 4;

    Integer OTPGenerationException = 5;

    Integer OTPExpiredException = 6;

    Integer OTPVerificationException = 7;

    Integer ForeignKeyViolationException = 8;

    Integer DuplicateEntryException = 9;

    Integer CreateFailedException = 10;

    Integer UpdateFailedException = 11;

    Integer RegistrationFailedException = 12;

    Integer NotFoundException = 13;

    Integer InternalServerException = 14;

    Integer InvalidPropertiesException = 15;

    Integer InvalidEndpointException = 16;

    Integer UnexpectedException = 17;

    Integer UnimplementedException = 18;

    Integer HealthCheckFailedException = 19;
}
