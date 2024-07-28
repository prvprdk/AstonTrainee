package org.example.repo.imp;

import org.example.config.SessionFactoryConfigure;
import org.example.entity.Audience;
import org.example.repo.AudienceRepo;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class AudienceRepoImp implements AudienceRepo {
    private final static String HQL_QUERY_BY_ID = """
            select a from Audience a
            LEFT JOIN FETCH a.students
            LEFT JOIN FETCH a.direction
             where a.id = :id
            """;

    @Override
    public void add(Audience audience) {

        try (Session session = SessionFactoryConfigure.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.persist(audience);
            transaction.commit();
        }

    }

    @Override
    public void update(Audience update) {

        try (Session session = SessionFactoryConfigure.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Audience audience = Optional.of(session.find(Audience.class, update.getId())).orElseThrow(NullPointerException::new);
            if (update.getName() != null) audience.setName(update.getName());

            transaction.commit();
        }
    }

    @Override
    public void deleteById(Integer id) {

        try (Session session = SessionFactoryConfigure.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Audience audience = Optional.of(session.find(Audience.class, id)).orElseThrow(NullPointerException::new);
            session.remove(audience);
            transaction.commit();
        }

    }

    @Override
    public Optional<Audience> findById(Integer id) {

        try (Session session = SessionFactoryConfigure.getSessionFactory().openSession()) {
            List<Audience> resultList = session.createQuery(HQL_QUERY_BY_ID, Audience.class).setParameter("id", id).getResultList();
            if (resultList.isEmpty()) return Optional.empty();

            return Optional.of(resultList.get(0));
        }
    }

    @Override
    public List<Audience> findAll() {
        try (Session session = SessionFactoryConfigure.getSessionFactory().openSession()) {
            return session.createQuery("select a from Audience a", Audience.class).getResultList();

        }
    }
}
