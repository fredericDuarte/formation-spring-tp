package com.training.spring.bigcorp.utils;

import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.model.Measure;

import com.training.spring.bigcorp.repository.MeasureDao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public class MeasureDaoImpl implements MeasureDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Measure measure) {

        em.persist(measure);
    }

    @Override
    public Measure findById(Long aLong) {
       return em.find(Measure.class, aLong);
    }

    @Override
    public List<Measure> findAll() {

        return em.createQuery("select c from Measure c inner join c.captor s",
                Measure.class).getResultList();
    }

    @Override
    public void delete(Measure id) {
        em.remove(id);

    }








  /*  private NamedParameterJdbcTemplate jdbcTemplate;

    private H2DateConverter h2DateConverter;

    public MeasureDaoImpl(NamedParameterJdbcTemplate jdbcTemplate, H2DateConverter h2DateConverter) {
        this.jdbcTemplate = jdbcTemplate;
        this.h2DateConverter = h2DateConverter;
    }

    private static String SELECT_WITH_JOIN = "SELECT m.id, m.instant, m.value_in_watt, m.captor_id, c.name as  captor_name, c.site_id, s.name as site_name " +
            "FROM Measure m inner join Captor c on m.captor_id=c.id inner join site s on c.site_id = s.id ";



    @Override
    public void create(Measure measure) {

        jdbcTemplate.update("insert into MEASURE (id, instant, value_in_watt, captor_id) values (:id, :instant, :valueInWatt, :captorId)",
                new MapSqlParameterSource()
                        .addValue("id", measure.getId())
                        .addValue("instant", measure.getInstant())
                        .addValue("valueInWatt", measure.getValueInWatt())
                        .addValue("captorId", measure.getCaptor().getId())


        );
    }

    @Override
    public Measure findById(Long s) {

        try {
            return jdbcTemplate.queryForObject(SELECT_WITH_JOIN + " where m.id = :id ",
                    new MapSqlParameterSource("id", s)
                    , this::measureMapper
            );
        }catch (EmptyResultDataAccessException e) {
                return null;
            }
    }

    @Override
    public List<Measure> findAll() {

        return jdbcTemplate.query(SELECT_WITH_JOIN, this::measureMapper);
    }

    @Override
    public void update(Measure measure) {
        jdbcTemplate.update("update MEASURE set value_in_watt = :valueInWatt where id = :id",
                new MapSqlParameterSource()
                        .addValue("id", measure.getId())
                        .addValue("valueInWatt", measure.getValueInWatt()));


    }

    @Override
    public void deleteById(Long s) {

        jdbcTemplate.update("delete from MEASURE where id =:id",

                new MapSqlParameterSource("id", s));
    }


    private Measure measureMapper(ResultSet rs, int rowNum) throws SQLException {
        Site site = new Site(rs.getString("site_name"));
        site.setId(rs.getString("site_id"));
        Captor captor = new Captor(rs.getString("captor_name"), site);
        captor.setId(rs.getString("captor_id"));
        Measure measure = new Measure(h2DateConverter.convert(rs.getString("instant")),
                rs.getInt("value_in_watt"),
                captor);
        measure.setId(rs.getLong("id"));
        return measure;
    }

    */
}
