package com.oceaneconsulting.poc.util;

import org.hibernate.Session;

/**
 * Created by ubuntu on 3/20/14.
 */
public interface HibernateCallback<T> {
    void execute(Session session);
}
