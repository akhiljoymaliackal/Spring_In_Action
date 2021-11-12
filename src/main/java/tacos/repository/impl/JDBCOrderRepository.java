package tacos.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import tacos.domain.Orders;
import tacos.domain.Taco;
import tacos.repository.OrderRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JDBCOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInsert;
    private SimpleJdbcInsert orderTacoInsert;
    private ObjectMapper objectMapper;

    @Autowired
    public void JDBCOrderRepository(JdbcTemplate jdbcTemplate){
        this.orderInsert = new SimpleJdbcInsert(jdbcTemplate).
                withTableName("Taco_Order").
                usingGeneratedKeyColumns("id");

        this.orderTacoInsert = new SimpleJdbcInsert(jdbcTemplate).
                withTableName("Taco_Order_Tacos");

        this.objectMapper = new ObjectMapper();

    }

    @Override
    public Orders save(Orders orders) {
        orders.setPlacedAt(new Date());
        long orderId = saveTacoOrder(orders);
        for (Taco taco : orders.getDesign()){
            saveTacoOrderTacos(taco, orderId);
        }
        return orders;
    }

    private long saveTacoOrder(Orders orders){
        Map<String , Object> values = objectMapper.convertValue(orders, Map.class);
        values.put("placedAt",orders.getPlacedAt());
        long orderId = orderInsert.executeAndReturnKey(values).longValue();
        return orderId;
    }

    private void saveTacoOrderTacos(Taco taco, long orderId){
        Map<String, Object> values= new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco",taco.getId());
        orderTacoInsert.execute(values);
    }
}
