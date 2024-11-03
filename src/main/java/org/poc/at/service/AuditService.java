package org.poc.at.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.query.AuditEntity;
import org.poc.at.model.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class AuditService {
    
    AuditReader auditReader;

    public List<Object[]> getPublisherRevisions(Long publisherId) {        
        return auditReader.createQuery()
                .forRevisionsOfEntity(Publisher.class, false, true)
                .add(AuditEntity.id().eq(publisherId))
                .getResultList();
    }
}
