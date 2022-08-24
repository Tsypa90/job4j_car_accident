package ru.job4j.accident.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rules")
@Transactional
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public static Rule of(String name) {
        Rule rule = new Rule();
        rule.name = name;
        return rule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rule rule = (Rule) o;
        return id == rule.id && Objects.equals(name, rule.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
