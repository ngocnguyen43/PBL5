package service.impl;

import dao.interfaces.IScheduleDAO;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Schedules;
import service.interfaces.IScheduleService;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.util.List;

public class ScheduleService implements IScheduleService {
    @Inject
    private IScheduleDAO iScheduleDAO;

    @Override
    public Message FindAll() {
        List<Schedules> schedules = this.iScheduleDAO.FindAll();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        Data data = new Data.Builder(null).withResults(schedules).build();
        return new Message.Builder(meta).withData(data).build();
    }
}
