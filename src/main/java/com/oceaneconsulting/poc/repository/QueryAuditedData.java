package com.oceaneconsulting.poc.repository;

import java.util.Date;
import java.util.List;

/**
 * Created by ubuntu on 3/21/14.
 */
public interface QueryAuditedData<T> {

    T getAuditedEntity(int rev);

    Number getAuditedEntity(Date revisionTime);

    List getRevisions();



}
