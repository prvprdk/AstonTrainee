package org.example.repo.imp;

import org.example.entity.Direction;
import org.example.repo.DirectionRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Qualifier("directionRepo")
public class DirectionRepoImp implements DirectionRepo {

    private final static String HQL_QUERY_BY_ID = """
            select a from Direction a
            JOIN FETCH a.audiences
            where a.id = :id
            """;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Direction direction) {

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            session.persist(direction);
            transaction.commit();
        }

    }

    @Override
    public void update(Direction updatedirection) {

        try (Session session = sessionFactory.openSession()) {
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


        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            Direction direction = Optional.of(session.find(Direction.class, id)).orElseThrow(() -> new NullPointerException("oops"));
            session.remove(direction);
            transaction.commit();
        }

    }

    @Override
    public Optional<Direction> findById(Integer id) {

        try (Session session = sessionFactory.openSession()) {

            List<Direction> resultList = session.createQuery(HQL_QUERY_BY_ID, Direction.class).setParameter("id", id).getResultList();
            if (resultList.isEmpty()) return Optional.empty();
            return Optional.ofNullable(resultList.get(0));

        }
    }

    @Override
    public List<Direction> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select a from Direction a", Direction.class).getResultList();
        }
    }
}
