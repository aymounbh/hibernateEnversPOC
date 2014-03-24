package com.oceaneconsulting.poc.repository.spring;

import com.oceaneconsulting.poc.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ubuntu on 3/24/14.
 */
@Repository(value = "userRepository")
@Transactional(readOnly = true)
public class UserRepository{

    @Autowired
    SessionFactory sessionFactory;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void create(User user) {
        sessionFactory.getCurrentSession().save(user);
    }
    @Transactional
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Transactional
    public User get(long id) {
       return (User) sessionFactory.getCurrentSession().get(User.class, id);
    }
}
