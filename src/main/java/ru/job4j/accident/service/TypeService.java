package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.TypeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeService {
    private final TypeRepository store;

    public TypeService(TypeRepository store) {
        this.store = store;
    }

    public List<AccidentType> getTypes() {
        List<AccidentType> rsl = new ArrayList<>();
        store.findAll().forEach(rsl::add);
        return rsl;
    }

    public AccidentType findTypeById(int id) {
        return store.findById(id);
    }
}
