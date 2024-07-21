package org.example.db;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.flywaydb.core.Flyway;

@WebListener
public class FlyWayMigration implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String url = "jdbc:postgresql://localhost:5432/my-db";
        String user = "postgres";
        String password = "mysecretpassword";
        Flyway flyway = Flyway.configure()
                .dataSource(url, user, password)
                .driver("org.postgresql.Driver")

                .load();

        flyway.migrate();


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }


}
