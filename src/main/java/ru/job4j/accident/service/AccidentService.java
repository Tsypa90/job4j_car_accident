package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentRepository store;

    @Autowired
    public AccidentService(AccidentRepository store) {
        this.store = store;
    }

    @Transactional
    public List<Accident> findAll() {
        return store.findByOrderByIdAsc();
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
}
