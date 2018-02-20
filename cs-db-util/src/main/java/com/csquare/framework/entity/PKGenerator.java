package com.csquare.framework.entity;

import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;


public class PKGenerator implements IdentifierGenerator, Configurable {

    public static final String NAME = "PKGenerator";

    public static final String CLASS = "com.csquare.framework.entity.PKGenerator";

    private String entityName;
    private String propertyName;

    /**
     * Getter for property 'entityName'.
     *
     * @return Value for property 'entityName'.
     */
    public String getEntityName() {

        return entityName;
    }

    /**
     * Getter for property 'propertyName'.
     *
     * @return Value for property 'propertyName'.
     */
    public String getPropertyName() {

        return propertyName;
    }

    @Override
    public Serializable generate(SessionImplementor sessionImplementor, Object object) throws HibernateException {

        // Map<EntityKey, ?> entries = session.getPersistenceContext().getEntitiesByKey();
        // Set<EntityKey> keySet = entries.keySet();
        // for (EntityKey key : keySet) {
        // Serializable s = key.getIdentifier();
        // Object value = entries.get(key);
        // value.getClass().getAnnotation(annotationClass)
        // System.out.println("");
        // }

        // Session session = (Session) sessionImplementor;
        //
        // final EntityPersister persister = sessionImplementor.getFactory().getEntityPersister(entityName);
        // Object associatedObject = persister.getPropertyValue(object, propertyName);
        // if (associatedObject == null) {
        // throw new IdentifierGenerationException("attempted to assign id from null one-to-one property [" + "]");
        // }
        //
        // final EntityType foreignValueSourceType;
        // final Type propertyType = persister.getPropertyType(propertyName);
        // if (propertyType.isEntityType()) {
        // // the normal case
        // foreignValueSourceType = (EntityType) propertyType;
        // } else {
        // // try identifier mapper
        // foreignValueSourceType = (EntityType) persister.getPropertyType(PropertyPath.IDENTIFIER_MAPPER_PROPERTY + "." + propertyName);
        // }

        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {

        // TODO Auto-generated method stub

    }
}
