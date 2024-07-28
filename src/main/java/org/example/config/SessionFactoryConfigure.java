package org.example.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@WebListener
public class SessionFactoryConfigure implements ServletContextListener {
    private static SessionFactory sessionFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Direction.class)
                .addAnnotatedClass(Audience.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(LocalStudent.class)
                .addAnnotatedClass(DistantStudent.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
