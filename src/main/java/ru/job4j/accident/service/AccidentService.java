package ru.job4j.accident.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {
    private final AccidentHibernate store;

    @Autowired
    public AccidentService(AccidentHibernate store) {
        this.store = store;
    }

    public List<Accident> findAll() {
        return store.findAll();
    }

    public Accident save(Accident accident) {
        return store.save(accident);
    }

    public Accident findById(int id) {
        return store.findById(id);
    }

    public void edit(Accident accident) {
        store.save(accident);
    }

    public List<Rule> getRules() {
        return store.getRules();
    }

    public Rule findRuleById(int id) {
        return store.findRuleById(id);
    }

    public List<AccidentType> getTypes() {
        return store.getTypes();
    }

    public AccidentType findTypeById(int id) {
        return store.findTypeById(id);
    }
}
