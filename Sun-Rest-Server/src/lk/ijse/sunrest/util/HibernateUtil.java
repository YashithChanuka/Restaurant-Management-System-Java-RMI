package lk.ijse.sunrest.util;

import lk.ijse.sunrest.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .loadProperties("lk/ijse/sunrest/util/hibernate.properties").build();

        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Chef.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(OrderDetail.class)
                .addAnnotatedClass(Admin.class)
                .buildMetadata();
        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory(){return sessionFactory;}

}
