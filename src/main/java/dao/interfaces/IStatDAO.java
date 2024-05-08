package dao.interfaces;

import model.Stat;

public interface IStatDAO extends DAOInterface<Stat> {
    Stat GetPercentUsersBuy();
    Stat GetTotalRevenue();
}
