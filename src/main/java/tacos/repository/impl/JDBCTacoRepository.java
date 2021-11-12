package tacos.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tacos.domain.Ingredient;
import tacos.domain.Taco;
import tacos.repository.TacoRepository;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JDBCTacoRepository implements TacoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCTacoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Taco save(Taco taco) {
        Long tacoID = saveTacoInfo(taco);
        for (String ingredient : taco.getIngredients()){
            saveIngredientToTaco(ingredient, tacoID);
        }
        return taco;
    }

    private Long saveTacoInfo(Taco taco){
        taco.setCreatedAt(new Date());

        PreparedStatementCreator psc =
                new PreparedStatementCreatorFactory(
                        "insert into Taco (name, createdAt) values (?, ?)",
                        Types.VARCHAR,Types.TIMESTAMP).newPreparedStatementCreator(Arrays.asList(taco.getName(),taco.getCreatedAt()));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc,keyHolder);
        taco.setId(keyHolder.getKey().longValue());
        return taco.getId();
    }

    private void saveIngredientToTaco(String ingredient, Long tacoID){
        jdbcTemplate.update("insert into Taco_Ingredient (taco, ingredient) values (?, ?)", tacoID, ingredient);
    }
}
