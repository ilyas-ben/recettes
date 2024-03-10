package ma.ac.uir.dao;


import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class HibernateUtil {
    public static final SessionFactory sessionFactory;
 
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml)
    
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            
            
        } catch (Throwable ex) {
            // Log the exception.
            System.err.println("Initial SessionFactory creation failed." + ex);
           throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
