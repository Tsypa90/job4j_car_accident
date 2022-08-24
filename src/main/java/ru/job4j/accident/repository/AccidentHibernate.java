package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.persist(accident);
            session.getTransaction().commit();
            session.close();
            return accident;
        }
    }

    @Transactional
    public List<Accident> findAll() {
        try (Session session = sf.openSession()) {
            var list = session
                    .createQuery("select distinct a from Accident a "
                            + "join fetch a.type "
                            + "join fetch a.rules order by a.id asc ", Accident.class).getResultList();
            System.out.println(list.size());
            return list;
        }
    }

    public List<Rule> getRules() {
        try (Session session = sf.openSession()) {
            return session.createQuery("select r from Rule r ", Rule.class)
                    .list();
        }
    }

    public List<AccidentType> getTypes() {
        try (Session session = sf.openSession()) {
            return session.createQuery("select a from AccidentType a ", AccidentType.class)
                    .list();
        }
    }

    public Accident findById(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "select distinct a from Accident a "
                            + "join fetch a.type "
                            + "join fetch a.rules "
                            + "where a.id = :cId", Accident.class).
                    setParameter("cId", id).uniqueResult();
        }
    }

    public void edit(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.update(accident);
            session.getTransaction().commit();
            session.close();
        }
    }

    public AccidentType findTypeById(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "select distinct a from AccidentType a where a.id = :aId",
                    AccidentType.class).setParameter("aId", id).uniqueResult();
        }
    }

    public Rule findRuleById(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "select distinct a from Rule a where a.id = :aId",
                    Rule.class).setParameter("aId", id).uniqueResult();
        }
    }
}
