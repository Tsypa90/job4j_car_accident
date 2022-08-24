package ru.job4j.accident.service;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {
    private AccidentRepository store;

    public AccidentService(AccidentRepository store) {
        this.store = store;
    }

    @Transactional
    public List<Accident> findAll() {
        List<Accident> rsl = new ArrayList<>();
        store.findByOrderByIdAsc().forEach(s -> {
            Hibernate.initialize(s.getRules());
            Hibernate.initialize(s.getType());
            rsl.add(s);
        });
        return rsl;
    }

    public Accident save(Accident accident) {
        return store.save(accident);
    }

    @Transactional
    public Accident findById(int id) {
        return store.findById(id);
    }

    public void edit(Accident accident) {
        store.save(accident);
    }


}
