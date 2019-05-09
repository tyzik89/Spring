package com.work.vladimirs.shawermacloud.repositories;

import com.work.vladimirs.shawermacloud.entity.Ingredient;
import com.work.vladimirs.shawermacloud.entity.Shawerma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcShawermaRepository implements ShawemaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Shawerma save(Shawerma newShawerma) {
        long shawermaId = saveShawermaInfo(newShawerma);
        newShawerma.setId(shawermaId);
        for (String ingredient : newShawerma.getIngredients()) {
            saveIngredientToShawerma(ingredient, shawermaId);
        }
        return newShawerma;
    }

    private long saveShawermaInfo(Shawerma shawerma) {
        shawerma.setCreateAt(new Date());
        PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
                "insert into Shawerma (name, createdAt) values (?, ?)",
                Types.VARCHAR, Types.TIMESTAMP
        ).newPreparedStatementCreator(
                Arrays.asList(
                        shawerma.getName(),
                        new Timestamp(shawerma.getCreateAt().getTime())));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToShawerma(String ingredient, long tacoId) {
        jdbcTemplate.update(
                "insert into Shawerma_Ingredients (taco, ingredient) values (?, ?)",
                tacoId, ingredient);
    }
}
