package ru.job4j.accident.repository;

//import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

import java.util.List;

public interface AccidentRepository {
    List<Accident> findByOrderByIdAsc();
    Accident findById(int id);
}
