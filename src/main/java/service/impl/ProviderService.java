package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import dao.interfaces.ICustomerDAO;
import dao.interfaces.IProviderDAO;
import dao.interfaces.IUserDAO;
import dto.ProviderDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import model.Provider;
import model.User;
import service.interfaces.IProviderService;
import utils.exceptions.api.DatabaseOperationException;
import utils.exceptions.api.RegistrationFailedException;
import utils.helper.Helper;
import utils.helper.IDGenerator;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProviderService implements IProviderService {
    private final Logger logger = Logger.getLogger(AuthService.class.getName());
    @Inject
    private IProviderDAO iProviderDAO;
    @Inject
    private IUserDAO iUserDAO;
    @Inject
    private ICustomerDAO iCustomerDAO;

    @Override
    public Message FindAllProvider() {
        List<User> providers = this.iProviderDAO.FindAll();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();

        Data data = new Data.Builder(null).withResults(providers).build();
        return new Message.Builder(meta).withData(data).build();

    }

    @Override
    public Message FindOneByID(String ProviderId) {
        Provider Providers = iProviderDAO.FindOneById(ProviderId);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();

        Data data = new Data.Builder(null).withResults(Providers).build();
        return new Message.Builder(meta).withData(data).build();

    }

    public Message InsertProvider(ProviderDto providerDto) throws RegistrationFailedException, JsonProcessingException {
//        if (providerDto.getEmail() == null) {
//            throw new RegistrationFailedException();
//        }
//
//        String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
//        Pattern pattern = Pattern.compile(emailRegex);
//        Matcher matcher = pattern.matcher(providerDto.getEmail());
//        if (!matcher.matches()) {
//            throw new RegistrationFailedException();
//        }
//
//        boolean isExist = this.iProviderDAO.FindOneByEmail(providerDto.getEmail()) != null;
//        if (isExist) {
//            throw new RegistrationFailedException();
//        }
//
//        String userId = IDGenerator.generate(10);
//        String providerId = IDGenerator.generate(10);
//
//        Provider provider = Helper.objectMapper(providerDto, Provider.class);
//        User user = Helper.objectMapper(providerDto, User.class);
//        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(provider));
//        provider.setProvidersId(providerId);
//        provider.setUserId(userId);
//        user.setUserId(userId);
//        user.setPassword(HashPassword.Hash(user.getPassword()));
//        user.setRoleId("2");
//
//        try {
//            this.iUserDAO.CreateOne(user);
//            this.iProviderDAO.CreateOne(provider);
//            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
//            return new Message.Builder(meta).build();
//        } catch (Exception e) {
//            logger.log(Level.INFO, e.getMessage());
//            throw new RegistrationFailedException();
//        }
        return null;
    }

    public Message RequestProvider(ProviderDto providerDto) throws RegistrationFailedException {
        System.out.println("Provider: " + providerDto.toString());
        Customer customer = this.iCustomerDAO.FindOneByUserId(providerDto.getUserId());
        System.out.println("Customer: " + customer.toString());

        Provider provider = Helper.objectMapper(providerDto, Provider.class);
        provider.setFullName(customer.getFullName());
        provider.setEmail(customer.getEmail());
        provider.setPosition(customer.getPosition());
        provider.setPhoneNumber(customer.getPhoneNumber());
        provider.setUserId(customer.getUserId());

        String providerId = IDGenerator.generate(10);
        provider.setProvidersId(providerId);

        System.out.println("Provider New:" + provider);
        try {
            this.iProviderDAO.CreateOne(provider);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            throw new RegistrationFailedException();
        }
    }

    @Override
    public Message UpdateProvider(ProviderDto ProviderDto) throws DatabaseOperationException {
        Provider provider = Helper.objectMapper(ProviderDto, Provider.class);
        try {
            iProviderDAO.UpdateOne(provider);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    @Override
    public Message ConfirmProvider(ProviderDto providerDto) throws DatabaseOperationException {
        Provider provider = Helper.objectMapper(providerDto, Provider.class);
        try {
            iProviderDAO.ConfirmProvider(provider);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    public Message DeleteProvider(ProviderDto providerDto) throws DatabaseOperationException {
        System.out.println("pro DTO: " + providerDto.toString());
        Provider provider = Helper.objectMapper(providerDto, Provider.class);
        System.out.println("Pro Service: " + provider.toString());
        try {
            Provider provider1 = iProviderDAO.FindOneById(provider.getProvidersId());
            String userId = provider1.getUserId();
            iProviderDAO.DeleteOneById(provider.getProvidersId());
            iUserDAO.DeleteOneById(userId);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            throw new DatabaseOperationException(e.getMessage());
        }
    }
}
