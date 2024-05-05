package service.interfaces;

import model.User;
import utils.response.Message;

public interface IUserService {
    Message FindOne(String id);
    Message FindRole(String id);
}
