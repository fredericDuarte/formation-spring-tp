package com.training.spring.bigcorp.utils;

import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.model.Site;
import com.training.spring.bigcorp.repository.CaptorDao;
import com.training.spring.bigcorp.repository.SiteDao;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CaptorDaoImpl implements CaptorDao {

    private NamedParameterJdbcTemplate jdbcTemplate;


    private static String SELECT_WITH_JOIN =
            "SELECT c.id, c.name, c.site_id, s.name as site_name " +
                    "FROM Captor c inner join Site s on c.site_id = s.id ";


    public CaptorDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void create(Captor captor) {

        jdbcTemplate.update("insert into CAPTOR (id, name) values (:id, :name)",
                new MapSqlParameterSource()
                        .addValue("id", captor.getId())
                        .addValue("name", captor.getName()));

    }

    @Override
    public Captor findbyId(String s) {
        return jdbcTemplate.queryForObject("select id, name from CAPTOR where id = :id ",
                new MapSqlParameterSource("id", s),
                this::captorMapper);
    }

    @Override
    public List<Captor> findAll() {
        return jdbcTemplate.query(SELECT_WITH_JOIN, this::captorMapper);
    }

    @Override
    public void update(Captor captor) {

        jdbcTemplate.update("update CAPTOR set name = :name where id =:id",
                new MapSqlParameterSource()
                        .addValue("id", captor.getId())
                        .addValue("name", captor.getName()));

    }

    @Override
    public void deleteByID(String s) {
        jdbcTemplate.update("delete from CAPTOR where id =:id",
                new MapSqlParameterSource("id", s));

    }


    private Captor captorMapper(ResultSet rs, int rowNum) throws SQLException {
        Site site = new Site(rs.getString("site_name"));
        site.setId(rs.getString("site_id"));
        Captor captor = new Captor(rs.getString("name"), site);
        captor.setId(rs.getString("id"));
        return captor;
    }


    @Override
    public List<Captor> findBySiteId(String siteId) {
        return null;
    }
}
