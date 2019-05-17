package com.work.vladimirs.shawermacloud.repositories.JDBCTemplate;

import com.work.vladimirs.shawermacloud.entity.Ingredient;
import com.work.vladimirs.shawermacloud.entity.Shawerma;
import com.work.vladimirs.shawermacloud.repositories.ShawemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
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

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        "insert into Shawerma (name, createdAt) values (?, ?)",
                        new String[] {"id"}
                );
                ps.setString(1, shawerma.getName());
                ps.setTimestamp(2, new Timestamp(shawerma.getCreateAt().getTime()));
                return ps;
            }
        };

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToShawerma(String ingredient, long tacoId) {
        jdbcTemplate.update(
                "insert into Shawerma_Ingredients (shawerma, ingredient) values (?, ?)",
                tacoId, ingredient);
    }
}
