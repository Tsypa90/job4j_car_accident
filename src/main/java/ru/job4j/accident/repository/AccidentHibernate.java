package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.function.Function;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;
    private static final String SELECT_ACCIDENT = "select distinct a from Accident a ";
    private static final String JOIN = "join fetch a.type join fetch a.rules ";
    private static final String ORDER = "order by a.id asc ";
    private static final String WHERE = "where a.id = :aId ";
    private static final String SELECT_RULE = "select distinct a from Rule a ";
    private static final String SELECT_TYPE = "select distinct a from AccidentType a ";

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private  <T> T tx(final Function<Session, T> command, SessionFactory sf) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Accident save(Accident accident) {
        return tx(session -> {
            session.persist(accident);
            return accident; }, sf);
    }

    public List<Accident> findAll() {
        return tx(session -> session.createQuery(SELECT_ACCIDENT + JOIN + ORDER, Accident.class).getResultList(), sf);
    }

    public List<Rule> getRules() {
        return tx(session -> session.createQuery(SELECT_RULE, Rule.class).getResultList(), sf);
    }

    public List<AccidentType> getTypes() {
        return tx(session -> session.createQuery(SELECT_TYPE, AccidentType.class).getResultList(), sf);
    }

    public Accident findById(int id) {
        return tx(session -> session
                .createQuery(SELECT_ACCIDENT + JOIN + WHERE, Accident.class)
                .setParameter("aId", id)
                .uniqueResult(), sf);
    }

    public void edit(Accident accident) {
        tx(session -> {
            session.update(accident);
            return accident; }, sf);
    }

    public AccidentType findTypeById(int id) {
        return tx(session -> session.
                createQuery(SELECT_TYPE + WHERE, AccidentType.class).setParameter("aId", id).uniqueResult(), sf);
    }

    public Rule findRuleById(int id) {
        return tx(session -> session.
                createQuery(SELECT_RULE + WHERE, Rule.class).setParameter("aId", id).uniqueResult(), sf);
    }
}
