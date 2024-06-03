package service.impl;

import dao.interfaces.IStationDAO;
import dto.StationDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Station;
import service.interfaces.IStationService;
import utils.exceptions.server.InternalServerException;
import utils.helper.Helper;
import utils.helper.IDGenerator;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.util.List;

public class StationService implements IStationService {
    @Inject
    private IStationDAO iStationDAO;

    @Override
    public Message FindAll(String name) {
        List<Station> stations;
        if (name == null) {
            stations = this.iStationDAO.FindAll();
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();

            Data data = new Data.Builder(null).withResults(stations).build();
            return new Message.Builder(meta).withData(data).build();
        } else {
            stations = this.iStationDAO.FindAllByName(name);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();

            Data data = new Data.Builder(null).withResults(stations).build();
            return new Message.Builder(meta).withData(data).build();
        }
    }

    @Override
    public Message FIndOne(String id) {
        return null;
    }

    @Override
    public Message CreateOne(StationDto stationDto) throws InternalServerException {
        Station station = Helper.objectMapper(stationDto, Station.class);
        station.setStationId(IDGenerator.generate(10));
        try {
            this.iStationDAO.CreateOne(station);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            Data data = new Data.Builder(null).withResults(station).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }
}
