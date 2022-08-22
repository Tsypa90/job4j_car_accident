package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;
import java.util.Set;

@Service
public class AccidentService {
    private AccidentJdbcTemplate store;

    public AccidentService(AccidentJdbcTemplate store) {
        this.store = store;
    }

    public List<Accident> findAll() {
        return store.findAll();
    }

    public List<AccidentType> getTypes() {
        return store.getTypes();
    }

    public List<Rule> getRules() {
        return store.getRules();
    }

    public Accident save(Accident accident) {
        return store.save(accident);
    }

    public Accident setAccident(Accident accident, int typeId, String[] rulesId) {
        accident.setType(store.findTypeById(typeId));
        for (String ruleId : rulesId) {
            accident.getRules().add(store.findRuleById(Integer.parseInt(ruleId)));
        }
        return accident;
    }

    public Accident findById(int id) {
        return store.findById(id);
    }

    public Accident edit(Accident accident) {
        return store.edit(accident);
    }
}
