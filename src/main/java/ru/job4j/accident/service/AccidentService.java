package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
public class AccidentService {
    private AccidentMem mem;

    public AccidentService(AccidentMem mem) {
        this.mem = mem;
    }

    public List<Accident> findAll() {
        return mem.findAll();
    }

    public List<AccidentType> getTypes() {
        return mem.getTypes();
    }

    public List<Rule> getRules() {
        return mem.getRules();
    }

    public Accident save(Accident accident) {
        return mem.save(accident);
    }

    public Accident findById(int id) {
        return mem.findById(id);
    }

    public Accident edit(Accident accident) {
        return mem.edit(accident);
    }
}
