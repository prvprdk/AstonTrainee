package org.example.repo.imp;

import org.example.config.SessionFactoryConfigure;
import org.example.entity.Student;
import org.example.repo.StudentRepo;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class StudentRepoImp implements StudentRepo {
    private final static String HQL_QUERY_BY_ID = """
            select a from Student a
            LEFT JOIN FETCH a.classes c
            LEFT JOIN FETCH c.direction
            where a.id = :id
            """;

    @Override
    public void add(Student student) {

        try (Session session = SessionFactoryConfigure.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
        }
    }

    @Override
    public void update(Student updateStudent) {

        try (Session session = SessionFactoryConfigure.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = Optional.of(session.find(Student.class, updateStudent.getId())).orElseThrow(NullPointerException::new);
            if (updateStudent.getName() != null) student.setName(updateStudent.getName());

            transaction.commit();
        }
    }

    @Override
    public void deleteById(Integer id) {

        try (Session session = SessionFactoryConfigure.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = Optional.of(session.find(Student.class, id)).orElseThrow(NullPointerException::new);
            session.remove(student);
            transaction.commit();
        }

    }

    @Override
    public Optional<Student> findById(Integer id) {

        try (Session session = SessionFactoryConfigure.getSessionFactory().openSession()) {
            List<Student> resultList = session.createQuery(HQL_QUERY_BY_ID, Student.class).setParameter("id", id).getResultList();
            if (resultList.isEmpty()) return Optional.empty();

            return Optional.ofNullable(resultList.get(0));
        }
    }

    @Override
    public List<Student> findAll() {
        try (Session session = SessionFactoryConfigure.getSessionFactory().openSession()) {
            return session.createQuery("select a from Student a", Student.class).getResultList();
        }
    }
}
