package service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dao.interfaces.IUserDAO;
import dto.CustomerDto;
import dto.TokenDto;
import dto.UserDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import org.codehaus.jackson.map.ObjectMapper;
import service.interfaces.IAuthService;
import utils.contants.ENV;
import utils.exceptions.api.BadRequestException;
import utils.exceptions.api.InvalidCredentialsException;
import utils.exceptions.server.InternalServerException;
import utils.helper.DecryptPassword;
import utils.helper.HashPassword;
import utils.helper.Helper;
import utils.helper.IDGenerator;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthService implements IAuthService {
    private final Logger logger = Logger.getLogger(AuthService.class.getName());
    @Inject
    private IUserDAO iUserDAO;

    @Override
    public Message Register(CustomerDto dto) throws BadRequestException, InternalServerException {
        if (dto.getEmail() == null) {
            throw new BadRequestException("Invalid user cre");
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(dto.getEmail());
        if (!matcher.matches()) {
            throw new BadRequestException("Invalid user cre");
        }

        String userId = IDGenerator.generate(10);
        User user = Helper.objectMapper(dto, User.class);

        user.setUserId(userId);
        user.setPassword(HashPassword.Hash(user.getPassword()));

        try {
            this.iUserDAO.CreateOne(user);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            if (e instanceof java.sql.SQLIntegrityConstraintViolationException) {
                throw new BadRequestException("Duplicate username or email");
            }
            throw new InternalServerException();
        }

    }

    @Override
    public Message Login(UserDto dto) throws InvalidCredentialsException {
        if (dto.getUsername() == null || dto.getPassword() == null)
            throw new InvalidCredentialsException(MessageResponse.INVALID_EMAIL_OR_PASSWORD);
        User user = iUserDAO.FindOneByUsername(dto.getUsername(), true);
        if (user == null) throw new InvalidCredentialsException(MessageResponse.INVALID_EMAIL_OR_PASSWORD);
        String hashedPassword = user.getPassword();
//        try {

        if (!DecryptPassword.Decrypt(dto.getPassword(), hashedPassword))
            throw new InvalidCredentialsException(MessageResponse.INVALID_EMAIL_OR_PASSWORD);
        try {
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Algorithm algorithm = Algorithm.HMAC256(ENV.SECRET);
        String token = JWT.create()
                .withExpiresAt(new Date(ENV.ACCESS_TOKEN_EXPIRES_TIME))
                .withClaim("user_id", user.getUserId())
                .withClaim("token_type","access")
                .sign(algorithm);
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccessToken(token);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.LOGIN_SUCCESS).build();
        Data data = new Data.Builder(null).withResults(tokenDto).build();
        return new Message.Builder(meta).withData(data).build();
    }
}
