package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        for (int i = 0; i < 10; i++) {
            accidents.put(i, Accident.of(i, "test" + i, "test" + i, "test" + i));
        }
    }

    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }
}
