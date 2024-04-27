package service.interfaces;

import dto.ProviderDto;
import utils.exceptions.api.DatabaseOperationException;
import utils.exceptions.api.RegistrationFailedException;
import utils.response.Message;

public interface IProviderService {
    Message FindAllProvider();
    Message FindOneByID(String customerId);
    Message InsertProvider(ProviderDto ProviderDto) throws RegistrationFailedException;
    Message RequestProvider(ProviderDto ProviderDto) throws RegistrationFailedException;
    Message UpdateProvider(ProviderDto ProviderDto) throws DatabaseOperationException;
    Message ConfirmProvider(ProviderDto ProviderDto) throws DatabaseOperationException;
    Message DeleteProvider(ProviderDto ProviderDto) throws DatabaseOperationException;
}
