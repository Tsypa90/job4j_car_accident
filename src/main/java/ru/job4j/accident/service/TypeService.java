package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.List;

@Service
public class TypeService {
    private final AccidentHibernate store;

    public TypeService(AccidentHibernate store) {
        this.store = store;
    }

    public List<AccidentType> getTypes() {
        return store.getTypes();
    }

    public AccidentType findTypeById(int id) {
        return store.findTypeById(id);
    }
}
