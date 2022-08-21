package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final List<AccidentType> types = new ArrayList<>();
    private final AtomicInteger id = new AtomicInteger(1);

    public AccidentMem() {
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));
    }

    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }

    public List<AccidentType> getTypes() {
        return types;
    }

    public Accident save(Accident accident) {
        int accidentId = id.getAndIncrement();
        accident.setId(accidentId);
        return accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public Accident edit(Accident accident) {
        return accidents.replace(accident.getId(), accident);
    }
}
