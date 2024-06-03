package service.impl;

import dao.interfaces.IStatDAO;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Stat;
import service.interfaces.IStatService;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

public class StatService implements IStatService {
    @Inject
    private IStatDAO iStatDAO;

    @Override
    public Message GetPercentUsersBuy() {
        Stat stat = this.iStatDAO.GetPercentUsersBuy();

        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        Data data = new Data.Builder(null).withResults(stat).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message GetTotalRevenue() {
        Stat stat = this.iStatDAO.GetTotalRevenue();

        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        Data data = new Data.Builder(null).withResults(stat).build();
        return new Message.Builder(meta).withData(data).build();
    }
}
