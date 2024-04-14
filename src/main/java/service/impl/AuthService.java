package service.impl;

import dao.interfaces.ICustomerDAO;
import dao.interfaces.IUserDAO;
import dto.CustomerDto;
import dto.UserDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import model.User;
import org.codehaus.jackson.map.ObjectMapper;
import service.interfaces.IAuthService;
import utils.exceptions.api.InvalidCredentialsException;
import utils.exceptions.api.RegistrationFailedException;
import utils.helper.DecryptPassword;
import utils.helper.HashPassword;
import utils.helper.Helper;
import utils.helper.IDGenerator;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthService implements IAuthService {
    private final Logger logger = Logger.getLogger(AuthService.class.getName());
    @Inject
    private ICustomerDAO iCustomerDAO;
    @Inject
    private IUserDAO iUserDAO;

    @Override
    public Message Register(CustomerDto dto) throws RegistrationFailedException {
        if(dto.getEmail()==null){
            throw new RegistrationFailedException();
        }

        String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(dto.getEmail());
        if (!matcher.matches()) {
            throw new RegistrationFailedException();
        }

        boolean isExist = this.iCustomerDAO.FindOneByEmail(dto.getEmail()) != null;
        if (isExist) {
            throw new RegistrationFailedException();
        }

        String userId = IDGenerator.generate(10);
        String customerId = IDGenerator.generate(10);

        Customer customer = Helper.objectMapper(dto, Customer.class);
        User user = Helper.objectMapper(dto, User.class);

        customer.setCustomerId(customerId);
        customer.setUserId(userId);
        user.setUserId(userId);
        user.setPassword(HashPassword.Hash(user.getPassword()));
        user.setRoleId("2");

        try {
            this.iUserDAO.CreateOne(user);
            this.iCustomerDAO.CreateOne(customer);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            throw new RegistrationFailedException();
        }

    }

    @Override
    public Message Login(UserDto dto) throws InvalidCredentialsException {
        if (dto.getUsername() == null || dto.getPassword() == null)
            throw new InvalidCredentialsException(MessageResponse.INVALID_EMAIL_OR_PASSWORD);
        User user = iUserDAO.FindOneByUsername(dto.getUsername(),true);
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
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.LOGIN_SUCCESS).build();
        return new Message.Builder(meta).build();
    }
}
