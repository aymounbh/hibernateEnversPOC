package com.oceaneconsulting.poc.repository;

import com.oceaneconsulting.poc.model.Book;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by ubuntu on 3/21/14.
 */
@Repository
public interface QueryAuditedData<T> {

    Integer getMinRevisionNumberForEntity(Class<Book> clazz);

    Integer getMaxRevisionNumberForEntity(Class<Book> clazz);

    T getAuditedEntityByRevisionNumber(int rev);

    Number getAuditedEntityByRevisionDate(Date revisionTime);

    List getRevisions();



}
