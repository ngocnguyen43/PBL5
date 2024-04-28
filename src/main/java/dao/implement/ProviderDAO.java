package dao.implement;

import dao.interfaces.IProviderDAO;
import model.Provider;
import utils.mapper.implement.ProviderMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProviderDAO extends AbstractDAO<Provider> implements IProviderDAO {

    @Override
    public Provider FindOneByEmail(String email) {
        String sql = "SELECT * FROM ticket_providers WHERE email = ?";
        List<Provider> providers = query(sql, new ProviderMapper(), email);
        return providers.isEmpty() ? null : providers.get(0);
    }

    @Override
    public List<Provider> FindAll() {
        String sql = "SELECT * FROM ticket_providers";
        return query(sql, new ProviderMapper());
    }

    public Provider FindOneById(String providerId) {
        String sql = "SELECT * FROM ticket_providers WHERE providers_id = ?";
        List<Provider> providers = query(sql, new ProviderMapper(), providerId);
        return providers.isEmpty() ? null : providers.get(0);
    }

    public Provider FindOneByUserId(String userId) {
        String sql = "SELECT * FROM ticket_providers WHERE user_id = ?";
        List<Provider> providers = query(sql, new ProviderMapper(), userId);
        return providers.isEmpty() ? null : providers.get(0);
    }

    @Override
    public void CreateOne(Provider provider) throws SQLException {
        String sql = "INSERT INTO ticket_providers (user_id," + "providers_id," + "full_name," + "email," + "phone_number," + "position," + "provider_name," + "contact_info," + "photo)" + " VALUES(?,?,?,?,?,?,?,?,?)";
        insert(sql, provider.getUserId(), provider.getProvidersId(), provider.getFullName(), provider.getEmail(), provider.getPhoneNumber(), provider.getPosition(), provider.getProviderName(), provider.getContactInfo(), provider.getPhoto());
    }

    public void CreateAll(ArrayList<Provider> arr) throws SQLException {
        for (Provider provider : arr) {
            CreateOne(provider);
        }
    }

    public void DeleteOneById(String provider_id) throws SQLException {
        String sql = "DELETE from ticketproviders WHERE providers_id =?";
        delete(sql, provider_id);
    }

    public void DeleteAll(ArrayList<Provider> arr) throws SQLException {
        for (Provider provider : arr) {
            DeleteOneById(provider.getProvidersId());
        }
    }

    public void UpdateOne(Provider provider) throws SQLException {
        String sql = "UPDATE ticketproviders " +
                " SET " +
                " full_name=?" +
                ", email=?" +
                ", phone_number=?" +
                ", position=?" +
                ", provider_name=?" +
                ", contact_info=?" +
                ", photo=?" +
                " WHERE providers_id=?";
        update(sql, provider.getFullName(), provider.getEmail(), provider.getPhoneNumber(), provider.getPosition(), provider.getProviderName(), provider.getContactInfo(), provider.getPhoto(), provider.getProvidersId());
    }

    public void ConfirmProvider(Provider provider) throws SQLException {
        String sql = "UPDATE ticket_providers " +
                " SET " +
                " is_confirmed=?" +
                " WHERE providers_id=?";
        update(sql, 1, provider.getProvidersId());
    }

}
