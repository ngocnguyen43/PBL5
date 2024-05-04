package service.impl;

import dao.interfaces.IUserDAO;
import dto.MeDto;
import dto.RoleNameDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import service.interfaces.IUserService;
import utils.helper.Helper;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

public class UserService implements IUserService {
    @Inject
    private IUserDAO iUserDAO;

    @Override
    public Message FindOne(String id) {
        User user = this.iUserDAO.FindOneByUserId(id, false);
        MeDto dto = Helper.objectMapper(user, MeDto.class);
        Data data = new Data.Builder(null).withResults(dto).build();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message FindRole(String id) {
        String roleName = this.iUserDAO.FindMyRole(id);
        RoleNameDto dto = new RoleNameDto();
        dto.setRole(roleName);
        Data data = new Data.Builder(null).withResults(dto).build();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        return new Message.Builder(meta).withData(data).build();
    }
}
