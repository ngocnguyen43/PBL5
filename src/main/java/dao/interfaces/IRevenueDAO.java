package dao.interfaces;

import model.Revenue;

import java.util.List;

public interface IRevenueDAO extends DAOInterface<Revenue> {
    List<Revenue> FindAll();
}
