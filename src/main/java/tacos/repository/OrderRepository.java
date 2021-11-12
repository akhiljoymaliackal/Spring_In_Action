package tacos.repository;

import tacos.domain.Orders;

public interface OrderRepository {
    Orders save(Orders orders);
}
