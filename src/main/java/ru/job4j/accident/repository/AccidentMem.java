package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private final Map<Integer, Rule> rules = new HashMap<>();
    private final AtomicInteger id = new AtomicInteger(1);

    public AccidentMem() {
    }

    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }

    public List<AccidentType> getTypes() {
        return new ArrayList<>(types.values());
    }

    public List<Rule> getRules() {
        return rules.values().stream().toList();
    }

    public Accident save(Accident accident) {
        int accidentId = id.getAndIncrement();
        accident.setId(accidentId);
        return accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public AccidentType findTypeById(int id) {
        return types.get(id);
    }

    public Rule findRuleById(int id) {
        return rules.get(id);
    }

    public Accident edit(Accident accident) {
        return accidents.replace(accident.getId(), accident);
    }
}
