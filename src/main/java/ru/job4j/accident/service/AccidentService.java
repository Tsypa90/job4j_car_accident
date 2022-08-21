package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
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

    public Accident save(Accident accident) {
        return mem.save(accident);
    }

    public Accident update(Accident accident) {
        return mem.update(accident);
    }

    public Accident getById(int id) {
        return mem.getById(id);
    }
}
