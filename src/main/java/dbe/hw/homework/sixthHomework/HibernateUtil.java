package dbe.hw.homework.sixthHomework;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    //https://dzone.com/articles/hibernate-5-java-configuration-example

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, "org.postgresql.Driver"); //driver class
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/store");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "1234");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.POOL_SIZE, 1); //JDBC connection pool
                settings.put(Environment.SHOW_SQL, "true"); //show SQL queries
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread"); //HB's automatic session context management
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(ShoppingCart.class).addAnnotatedClass(ShoppingCartItem.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}