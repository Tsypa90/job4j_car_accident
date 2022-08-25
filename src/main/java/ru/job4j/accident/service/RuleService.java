package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.List;

@Service
public class RuleService {
    private final AccidentHibernate store;

    public RuleService(AccidentHibernate store) {
        this.store = store;
    }

    public List<Rule> getRules() {
        return store.getRules();
    }

    public Rule findRuleById(int id) {
        return store.findRuleById(id);
    }
}
