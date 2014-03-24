package com.oceaneconsulting.poc.repository;

import com.oceaneconsulting.poc.model.Book;
import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;

import java.util.Date;
import java.util.List;

/**
 * Created by ubuntu on 3/21/14.
 */
public class QueryBookAuditedData implements QueryAuditedData<Book> {

    private Session session;

    public QueryBookAuditedData(Session session) {
        this.session = session;
    }

    @Override
    public Book getAuditedEntityByRevisionNumber(int rev) {
        final AuditReader auditReader = AuditReaderFactory.get(session);
        AuditQuery auditQuery = auditReader.createQuery().forEntitiesAtRevision(Book.class, rev);
        if (!auditQuery.getResultList().isEmpty()) {
        return (Book) auditQuery.getResultList().get(0);
        }
        return null;
    }

    @Override
    public Number getAuditedEntityByRevisionDate(Date revisionTime) {
        final AuditReader auditReader = AuditReaderFactory.get(session);
        return auditReader.getRevisionNumberForDate(revisionTime);
    }

    @Override
    public List getRevisions() {
        final AuditReader auditReader = AuditReaderFactory.get(session);;
        return auditReader.createQuery().
                forRevisionsOfEntity(Book.class, true, true).getResultList();
    }
}
