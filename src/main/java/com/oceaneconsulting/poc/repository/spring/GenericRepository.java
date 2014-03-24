package com.oceaneconsulting.poc.repository.spring;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ubuntu on 3/24/14.
 */
@Repository
public abstract class GenericRepository<T> implements AbstractRepository<T> {
    @Autowired
    SessionFactory sessionFactory;

}
