package service.interfaces;

import dto.StationDto;
import utils.exceptions.server.InternalServerException;
import utils.response.Message;

public interface IStationService {
    Message FindAll(String name);

    Message FIndOne(String id);

    Message CreateOne(StationDto stationDto) throws InternalServerException;
}
