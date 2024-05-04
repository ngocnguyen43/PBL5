package service.impl;

import dao.interfaces.IStationDAO;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Station;
import service.interfaces.IStationService;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.util.List;

public class StationService implements IStationService {
    @Inject
    private IStationDAO iStationDAO;

    @Override
    public Message FindAll() {
        List<Station> stations = this.iStationDAO.FindAll();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();

        Data data = new Data.Builder(null).withResults(stations).build();
        return new Message.Builder(meta).withData(data).build();
    }
}
