package utils.mapper.implement;

import model.Provider;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProviderMapper implements IMapper<Provider> {
    Logger logger = Logger.getLogger(ProviderMapper.class.getName());

    @Override
    public Provider mapRow(ResultSet result) {
        Provider provider = new Provider();
        try {
            provider.setProvidersId(result.getString("providers_id"));
            provider.setUserId(result.getString("user_id"));
            provider.setFullName(result.getString("full_name"));
            provider.setEmail(result.getString("email"));
            provider.setPhoneNumber(result.getString("phone_number"));
            provider.setPosition(result.getString("position"));
            provider.setProviderName(result.getString("provider_name"));
            provider.setContactInfo(result.getString("contact_info"));
            provider.setPhoto(result.getString("photo"));
            return provider;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
