package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private AtomicInteger id = new AtomicInteger(1);

    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }

    public Accident save(Accident accident) {
        int accidentId = id.getAndIncrement();
        accident.setId(accidentId);
        return accidents.put(accidentId, accident);
    }

    public Accident update(Accident accident) {
        return accidents.replace(accident.getId(), accident);
    }

    public Accident getById(int id) {
        return accidents.get(id);
    }
}
