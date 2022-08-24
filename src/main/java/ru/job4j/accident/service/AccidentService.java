package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;
import java.util.List;

@Service
public class AccidentService {
    private AccidentHibernate store;

    public AccidentService(AccidentHibernate store) {
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

    public Rule findRuleById(int id) {
        return store.findRuleById(id);
    }

    public AccidentType findTypeById(int id) {
        return store.findTypeById(id);
    }

    public Accident setAccident(Accident accident, int typeId, String[] rulesId) {
        accident.setType(store.findTypeById(typeId));
        for (String ruleId : rulesId) {
            var rule = store.findRuleById(Integer.parseInt(ruleId));
            accident.ruleAdd(rule);
        }
        return accident;
    }

    public Accident findById(int id) {
        return store.findById(id);
    }

    public void edit(Accident accident) {
        store.edit(accident);
    }


}
