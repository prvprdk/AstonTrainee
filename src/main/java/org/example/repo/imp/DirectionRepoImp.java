package org.example.repo.imp;

import org.example.config.SessionFactoryConfigure;
import org.example.entity.Direction;
import org.example.repo.DirectionRepo;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class DirectionRepoImp implements DirectionRepo {
    private final static String HQL_QUERY_BY_ID = """
            select a from Direction a
            LEFT JOIN FETCH a.audiences
            where a.id = :id
            """;

    @Override
    public void add(Direction direction) {

        try (Session session = SessionFactoryConfigure.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.persist(direction);
            transaction.commit();
        }

    }

    @Override
    public void update(Direction updatedirection) {

        try (Session session = SessionFactoryConfigure.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Direction direction = Optional.of(session.find(Direction.class, updatedirection.getId())).orElseThrow(NullPointerException::new);

            if (updatedirection.getNameDirection() != null)
                direction.setNameDirection(updatedirection.getNameDirection());
            if (updatedirection.getDescription() != null) direction.setDescription(updatedirection.getDescription());

            transaction.commit();
        }


    }

    @Override
    public void deleteById(Integer id) {

        try (Session session = SessionFactoryConfigure.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Direction direction = Optional.of(session.find(Direction.class, id)).orElseThrow(NullPointerException::new);
            session.remove(direction);
            transaction.commit();
        }

    }

    @Override
    public Optional<Direction> findById(Integer id) {

        try (Session session = SessionFactoryConfigure.getSessionFactory().openSession()) {

            List<Direction> resultList = session.createQuery(HQL_QUERY_BY_ID, Direction.class).setParameter("id", id).getResultList();
            if (resultList.isEmpty()) return Optional.empty();
            return Optional.ofNullable(resultList.get(0));

        }
    }

    @Override
    public List<Direction> findAll() {
        try (Session session = SessionFactoryConfigure.getSessionFactory().openSession()) {
            return session.createQuery("select a from Direction a", Direction.class).getResultList();
        }
    }
}
