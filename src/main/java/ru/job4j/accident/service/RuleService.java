package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {
    private final RuleRepository store;

    public RuleService(RuleRepository store) {
        this.store = store;
    }

    public List<Rule> getRules() {
        List<Rule> rsl = new ArrayList<>();
        store.findAll().forEach(rsl::add);
        return rsl;
    }

    public Rule findRuleById(int id) {
        return store.findById(id);
    }
}
