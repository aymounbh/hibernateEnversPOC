package com.oceaneconsulting.poc.repository.spring;

import com.oceaneconsulting.poc.model.Book;
import com.oceaneconsulting.poc.model.User;
import com.oceaneconsulting.poc.repository.QueryAuditedData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created by ubuntu on 3/24/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config-test.xml")
@TransactionConfiguration()
public class QueryBookAuditedDataRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    QueryAuditedData queryBookAuditedDataRepository;

    @Before
    public void initData() {
//        createUserData();
//        createBooksData();
    }

    @Test
    public void testGetMaxRevisionNumberForEntity() {
        System.out.println(queryBookAuditedDataRepository.getMaxRevisionNumberForEntity(Book.class));
        Assert.assertNotNull(queryBookAuditedDataRepository.getMaxRevisionNumberForEntity(Book.class));
    }

    @Test
    public void testGetAuditedEntityByRevisionNumber() throws Exception {
        queryBookAuditedDataRepository.getAuditedEntityByRevisionNumber(0);

    }


    @Test
    public void testGetAuditedEntityByRevisionDate() throws Exception {
//        createUserData();
    }

    @Test
    public void testGetRevisions() throws Exception {

    }

    @After
    public void tearDown() {
//        userRepository.

    }

    private void createUserData() {
        User user1 = new User("Aymen", "Ben Hmida");
        userRepository.create(user1);
        User user2 = new User("Aymen2", "Ben Hmida2");
        userRepository.create(user2);
        User user3 = new User("Aymen3", "Ben Hmida3");
        userRepository.create(user3);
        User user4 = new User("Aymen4", "Ben Hmida4");
        userRepository.create(user4);
        User user5 = new User("Aymen5", "Ben Hmida5");
        userRepository.create(user5);
    }

    private void createBooksData() {
        Book book1 = new Book("Scala In Action", "Nilanjan Raychaudhuri", "9781935182757");
        bookRepository.create(book1);
        Book book2 = new Book("lift In Action", "Timothy Perrett", "9781935182801");
        bookRepository.create(book2);
        Book book3 = new Book("Spring Recipes – A problem solution approach", "Josh Long", "978-1-4302-2499-0");
        bookRepository.create(book3);
        Book book4 = new Book("Professional Java Development with the Spring Framework", "Rod Johnson", "978-0764574832");
        bookRepository.create(book4);
    }

}
