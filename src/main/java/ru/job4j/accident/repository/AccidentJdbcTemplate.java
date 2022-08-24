package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(con -> {
                    PreparedStatement ps = con.prepareStatement("insert into accident (name, acdn_text, address, type_id) values(?, ?, ?, ?)",
                            new String[] {"id"});
                    ps.setString(1, accident.getName());
                    ps.setString(2, accident.getText());
                    ps.setString(3, accident.getAddress());
                    ps.setInt(4, accident.getType().getId());
                    return ps;
                }, keyHolder);
        accident.setId(keyHolder.getKey().intValue());
        accident.getRules().forEach(rule -> jdbc.update("insert into accident_rules (accident_id, rule_id) values (?, ?)",
                accident.getId(), rule.getId()));
        return accident;
    }

    public AccidentType findTypeById(int id) {
        return jdbc.queryForObject("select * from types where id = ?",
                (rs, row) -> {
            AccidentType type = new AccidentType();
            type.setId(rs.getInt("id"));
            type.setName(rs.getString("name"));
            return type;
                }, id);
    }

    public Rule findRuleById(int id) {
        return jdbc.queryForObject("select * from rules where id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, id);
    }

    public List<Accident> findAll() {
        var list = jdbc.query("select * from accident", (rs, row) -> {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            accident.setText(rs.getString("acdn_text"));
            accident.setAddress(rs.getString("address"));
            accident.setType(findTypeById(rs.getInt("type_id")));
            jdbc.query("select rule_id from accident_rules where accident_id = ?",
                    (rsN, rowN) -> findRuleById(rsN.getInt("rule_id")),
                    rs.getInt("id")).forEach(accident.getRules()::add);
            return accident;
        });
        return new ArrayList<>(list);
    }

    public List<AccidentType> getTypes() {
        return jdbc.query("select * from types", (rs, row) -> {
            AccidentType type = new AccidentType();
            type.setId(rs.getInt("id"));
            type.setName(rs.getString("name"));
            return type;
        });
    }

    public List<Rule> getRules() {
        return jdbc.query("select * from rules", (rs, row) -> {
            Rule rule = new Rule();
            rule.setId(rs.getInt("id"));
            rule.setName(rs.getString("name"));
            return rule;
        });
    }

    public Accident findById(int id) {
        Accident rsl = jdbc.queryForObject("select * from accident where id = ?", (rs, row) -> {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            accident.setText(rs.getString("acdn_text"));
            accident.setAddress(rs.getString("address"));
            accident.setType(findTypeById(rs.getInt("type_id")));
            return accident;
                }, id);
        return rsl;
    }

    public Accident edit(Accident accident) {
       jdbc.update("update accident set name = ?, acdn_text = ?, address = ?, type_id = ? where id = ?",
               accident.getName(), accident.getText(), accident.getAddress(), accident.getType().getId(), accident.getId());
       jdbc.update("delete from accident_rules where accident_id = ?", accident.getId());
       accident.getRules().forEach(rule -> jdbc.update("insert into accident_rules (accident_id, rule_id) values (?, ?)",
                accident.getId(), rule.getId()));
       return accident;
    }

}
