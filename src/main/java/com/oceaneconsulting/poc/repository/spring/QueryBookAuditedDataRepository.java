package com.oceaneconsulting.poc.repository.spring;

import com.oceaneconsulting.poc.model.Book;
import com.oceaneconsulting.poc.repository.QueryAuditedData;
import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by ubuntu on 3/24/14.
 */
@Repository
@Transactional(readOnly = true)
public class QueryBookAuditedDataRepository implements QueryAuditedData<Book> {

    private static Logger logger = LoggerFactory.getLogger(QueryBookAuditedDataRepository.class);

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Integer getMaxRevisionNumberForEntity(Class<Book> clazz) {
        AuditReader auditReader = AuditReaderFactory.get(sessionFactory.getCurrentSession());
        AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(clazz, false, true);
       return (Integer) auditQuery.addProjection(AuditEntity.revisionNumber().max()).getSingleResult();
    }

    @Override
    public Book getAuditedEntityByRevisionNumber(int rev) {

        AuditReader auditReader = AuditReaderFactory.get(sessionFactory.getCurrentSession());
        AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(Book.class, false, true);
        Object[] result = (Object[]) auditQuery.addProjection(AuditEntity.revisionNumber().max()).getSingleResult();
            logger.info("Book "+result[0]+" Revision "+result[1]);

        return null;
    }

    @Override
    public Number getAuditedEntityByRevisionDate(Date revisionTime) {
        AuditReader auditReader = AuditReaderFactory.get(sessionFactory.getCurrentSession());
        return null;
    }

    @Override
    public List getRevisions() {
        return null;
    }
}
