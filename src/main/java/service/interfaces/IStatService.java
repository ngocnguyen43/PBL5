package service.interfaces;

import utils.response.Message;

public interface IStatService {
    Message GetPercentUsersBuy();
    Message GetTotalRevenue();
}
