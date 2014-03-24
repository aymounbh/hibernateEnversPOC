package com.oceaneconsulting.poc.repository.spring;

import com.oceaneconsulting.poc.model.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ubuntu on 3/24/14.
 */
@Repository(value = "bookRepository")
@Transactional(readOnly = true)
public class BookRepository{
    @Autowired
    SessionFactory sessionFactory;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void create(Book book) {
        sessionFactory.getCurrentSession().save(book);
    }
    @Transactional
    public void delete(Book book) {
        sessionFactory.getCurrentSession().delete(book);
    }

    @Transactional
    public Book get(long id) {
        return (Book) sessionFactory.getCurrentSession().get(Book.class, id);
    }
}
