package com.oceaneconsulting.poc.repository.spring;

import com.oceaneconsulting.poc.model.Book;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ubuntu on 3/24/14.
 */
public interface AbstractRepository<T> {

    void create(T entity);

    void delete(T entity);

    T get(long id);
}
