package service.impl;

import dao.interfaces.IUserDAO;
import dto.MeDto;
import dto.RoleNameDto;
import dto.UserDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import service.interfaces.IUserService;
import utils.exceptions.api.BadRequestException;
import utils.exceptions.server.InternalServerException;
import utils.helper.Helper;
import utils.helper.ObjectMerge;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.sql.SQLIntegrityConstraintViolationException;

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

    @Override
    public Message UpdateOne(String id, UserDto userDto) throws BadRequestException, InternalServerException {
        if (id == null) throw new BadRequestException("Invalid properties");
        User user = Helper.objectMapper(userDto, User.class);
        User existUser = this.iUserDAO.FindOneByUserId(id, false);
        if (existUser == null) throw new BadRequestException("invalid properties");
        try {
            User merged = ObjectMerge.merge(user, existUser);
            this.iUserDAO.UpdateOne(merged);
            Data data = new Data.Builder(null).withResults(merged).build();
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            if (e instanceof SQLIntegrityConstraintViolationException) throw new BadRequestException("Duplicate entry");
            throw new InternalServerException();
        }
    }

    @Override
    public Message DeleteOne(String id) throws InternalServerException {
        try {
            this.iUserDAO.DeleteOneById(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }
}
