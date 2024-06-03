package service.impl;

import dao.interfaces.IRevenueDAO;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Revenue;
import service.interfaces.IRevenueService;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RevenueService implements IRevenueService {
    @Inject
    private IRevenueDAO iRevenueDAO;

    @Override
    public Message FindAll(String query) {
        List<Revenue> list = this.iRevenueDAO.FindAll();
        var revenues = list.stream()
                .collect(Collectors.groupingBy(
                        o -> {
                            switch (query) {
                                case "week" -> {
                                    return o.getOrderByWeek();
                                }
                                case "month" -> {
                                    return o.getStartOfMonth();
                                }
                                case "day" -> {
                                    return o.getOrderDayUnix();
                                }
                                default -> o.getOrderDayUnix();
                            }
                            return null;
                        },
                        Collectors.summingDouble(Revenue::getRevenue)
                )).entrySet().stream().map(e -> {
                    Map<String, Object> obj = new HashMap<>();
                    obj.put("time", e.getKey());
                    obj.put("revenue", e.getValue());
                    return obj;
                }).toList();

        Data data = new Data.Builder(null).withResults(revenues).build();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        return new Message.Builder(meta).withData(data).build();
    }
}
