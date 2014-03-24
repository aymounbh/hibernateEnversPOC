package com.oceaneconsulting.poc.repository.spring;

import com.oceaneconsulting.poc.model.Book;
import com.oceaneconsulting.poc.repository.QueryAuditedData;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by ubuntu on 3/24/14.
 */
@Repository
public class QueryBookAuditedDataRepository implements QueryAuditedData<Book> {

    private static Logger logger = LoggerFactory.getLogger(QueryBookAuditedDataRepository.class);

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Book getAuditedEntityByRevisionNumber(int rev) {
        return null;
    }

    @Override
    public Number getAuditedEntityByRevisionDate(Date revisionTime) {
        return null;
    }

    @Override
    public List getRevisions() {
        return null;
    }
}
