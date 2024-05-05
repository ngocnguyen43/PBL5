package service.impl;

import dao.interfaces.IOrderDAO;
import dao.interfaces.IRoleDAO;
import dao.interfaces.IUserPermissionDAO;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Order;
import model.Role;
import model.User;
import service.interfaces.IOrderService;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderService implements IOrderService {

    @Inject
    private IOrderDAO iOrderDAO;
    @Inject
    private IUserPermissionDAO iUserPermissionDAO;
    @Inject
    private IRoleDAO iRoleDAO;

    @Override
    public Message FindAllOrders(User user) {
        List<Role> roles = this.iRoleDAO.FindAll();
        List<Order> orders = new ArrayList<>();
        Role role = roles.stream().filter(e -> Objects.equals(e.getRoleId(), user.getRoleId())).findAny().orElse(null);
        assert role != null;
        if (Objects.equals(role.getRoleName(), "ADMIN")) {
            orders = this.iOrderDAO.FindAll();
        } else if (Objects.equals(role.getRoleName(), "CUSTOMER")) {
            orders = this.iOrderDAO.FindAll(user.getUserId());
        } else if (Objects.equals(role.getRoleName(), "PROVIDER")) {
            orders = this.iOrderDAO.FindAllForProvider(user.getUserId());
        }
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        Data data = new Data.Builder(null).withResults(orders).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message FindAllOrdersByUserId() {
        return null;
    }
}
