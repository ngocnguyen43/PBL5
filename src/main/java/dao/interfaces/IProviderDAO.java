package dao.interfaces;

import model.Provider;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IProviderDAO {
    Provider FindOneByEmail(String email);

    List<User> FindAll();

    Provider FindOneById(String TicketProvidersId);

    Provider FindOneByUserId(String UserId);

    void CreateOne(Provider Provider) throws SQLException;

    void CreateAll(ArrayList<Provider> arr) throws SQLException;

    void DeleteOneById(String TicketProviders_id) throws SQLException;

    void DeleteAll(ArrayList<Provider> arr) throws SQLException;

    void UpdateOne(Provider Provider) throws SQLException;

    void ConfirmProvider(Provider Provider) throws SQLException;
}
