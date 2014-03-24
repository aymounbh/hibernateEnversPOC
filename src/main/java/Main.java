import com.oceaneconsulting.poc.model.Book;
import com.oceaneconsulting.poc.model.User;
import com.oceaneconsulting.poc.repository.QueryAuditedData;
import com.oceaneconsulting.poc.repository.QueryBookAuditedData;
import com.oceaneconsulting.poc.util.HibernateCallback;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Date;
import java.util.Map;

/**
 * Created by ubuntu on 3/20/14.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
            for (Object key : metadataMap.keySet()) {
                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
                final String entityName = classMetadata.getEntityName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }

            createSomeTestData(session);



            IdentifierLoadAccess identifierLoadAccess = session.byId(Book.class);
            Book selected = (Book) identifierLoadAccess.load(1l);
            System.out.println(selected);

            selected.setAuthorName(selected.getAuthorName() + " Modified");

            insert(selected);

            QueryAuditedData<Book> q = new QueryBookAuditedData(session);
            System.out.println(q.getAuditedEntity(30));
            for (Object o : q.getRevisions()) {
                if (o instanceof Book) {
                    Book result = (Book) o;
                    System.out.println(result);
                }
            }

        } finally {
            session.close();
        }
    }

    private static <T> void insert(final T entity) {
        modify(new HibernateCallback<Object>() {
            @Override
            public void execute(Session session) {
               final Transaction transaction = session.beginTransaction();
                session.save(entity);
                transaction.commit();

            }
        }, ourSessionFactory.openSession());
    }

    private static <T> void modify(HibernateCallback<T> callback, Session session) {
        callback.execute(session);
    }

    protected static void createSomeTestData(Session session) {

        Book book1 = new Book("Scala In Action", "Nilanjan Raychaudhuri", "9781935182757");
        Book book2 = new Book("lift In Action", "Timothy Perrett", "9781935182801");
        Book book3 = new Book("Spring Recipes – A problem solution approach", "Josh Long", "978-1-4302-2499-0");
        Book book4 = new Book("Professional Java Development with the Spring Framework", "Rod Johnson", "978-0764574832");

        Transaction transaction = session.beginTransaction();

        session.save(book1);
        session.save(book2);
        session.save(book3);
        session.save(book4);


        User user1 = new User("Aymen", "Ben Hmida");
        User user2 = new User("Aymen2", "Ben Hmida2");
        User user3 = new User("Aymen3", "Ben Hmida3");
        User user4 = new User("Aymen4", "Ben Hmida4");
        User user5 = new User("Aymen5", "Ben Hmida5");

        session.save(user1);
        session.save(user2);
        session.save(user3);
        session.save(user4);
        session.save(user5);

        transaction.commit();
    }
}
