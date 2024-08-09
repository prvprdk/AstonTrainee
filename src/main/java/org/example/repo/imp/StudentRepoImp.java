package org.example.repo.imp;

import org.example.entity.Student;
import org.example.repo.StudentRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Qualifier("studentRepo")
public class StudentRepoImp implements StudentRepo {
    private final static String HQL_QUERY_BY_ID = """
            select a from Student a
            LEFT JOIN FETCH a.classes c
            LEFT JOIN FETCH c.direction
            where a.id = :id
            """;

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void add(Student student) {

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
        }
    }

    @Override
    public void update(Student updateStudent) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = Optional.of(session.find(Student.class, updateStudent.getId())).orElseThrow(NullPointerException::new);
            if (updateStudent.getName() != null) student.setName(updateStudent.getName());

            transaction.commit();
        }
    }

    @Override
    public void deleteById(Integer id) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = Optional.of(session.find(Student.class, id)).orElseThrow(NullPointerException::new);
            session.remove(student);
            transaction.commit();
        }

    }

    @Override
    public Optional<Student> findById(Integer id) {

        try (Session session = sessionFactory.openSession()) {
            List<Student> resultList = session.createQuery(HQL_QUERY_BY_ID, Student.class).setParameter("id", id).getResultList();
            if (resultList.isEmpty()) return Optional.empty();

            return Optional.ofNullable(resultList.get(0));
        }
    }

    @Override
    public List<Student> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select a from Student a", Student.class).getResultList();
        }
    }
}
