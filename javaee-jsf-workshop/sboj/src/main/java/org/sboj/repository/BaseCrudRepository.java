package org.sboj.repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Stream;

/**
 * A generic repository implementation based solely on JPA.
 *
 * @author Vasil Atanasov
 */

public abstract class BaseCrudRepository<E, PK extends Serializable> implements CrudRepository<E, PK> {

    private final Class<E> entityClass;

    @PersistenceContext(unitName = "sbojPU")
    protected EntityManager entityManager;

    protected BaseCrudRepository() {
        this.entityClass = initEntityClass();
    }

    @Override
    public Class<E> getEntityClass() {
        return this.entityClass;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public int count() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(entityClass)));
        return entityManager.createQuery(cq).getSingleResult().intValue();
    }

    @Override
    public E find(PK id) {
        try {
            return this.entityManager.find(this.entityClass, id);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public Optional<E> findOptional(PK id) {
        return Optional.ofNullable(find(id));
    }

    @Override
    public E save(E entity) {
        if (this.entityManager.contains(entity)) {
            return this.merge(entity);
        } else {
            try {
                this.entityManager.persist(entity);
                return entity;
            } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
                return null;
            }
        }
    }

    @Override
    public E merge(E entity) {
        try {
            return entityManager.merge(entity);
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            return null;
        }
    }

    @Override
    public void remove(E entity) {
        try {
            this.entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
        } catch (IllegalArgumentException | TransactionRequiredException e) {
        }
    }

    @Override
    public void remove(PK id) {
        this.remove(this.find(id));
    }

    @Override
    public List<E> findAll() {
        try {
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<E> cq = cb.createQuery(this.entityClass);
            Root<E> rootEntry = cq.from(this.entityClass);
            CriteriaQuery<E> all = cq.select(rootEntry);
            TypedQuery<E> allQuery = this.entityManager.createQuery(all);
            return allQuery.getResultList();
        } catch (IllegalStateException | IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<E> findAll(Order order, String... propertiesOrder) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(this.entityClass);
        Root<E> root = cq.from(this.entityClass);

        List<javax.persistence.criteria.Order> orders = new ArrayList<>();
        for (String propertyOrder : propertiesOrder) {
            if (order.isAscOrder()) {
                orders.add(cb.asc(root.get(propertyOrder)));
            } else {
                orders.add(cb.desc(root.get(propertyOrder)));
            }
        }
        cq.orderBy(orders);

        return this.entityManager.createQuery(cq).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findByNamedQuery(String queryName, Object... params) {
        try {
            Query query = this.entityManager.createNamedQuery(queryName);

            for (int i = 0; i < params.length; i++) {
                query.setParameter(i + 1, params[i]);
            }

            return (List<E>) query.getResultList();
        } catch (IllegalStateException | PersistenceException e) {
            return Collections.emptyList();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findByNamedQueryAndNamedParams(String queryName, Map<String, Object> params) {
        try {
            Query query = this.entityManager.createNamedQuery(queryName);

            for (final Map.Entry<String, Object> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }

            return (List<E>) query.getResultList();
        } catch (IllegalStateException | PersistenceException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<E> findByProperty(String propertyName, Object value) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<E> cq = cb.createQuery(this.entityClass);
            Root<E> root = cq.from(this.entityClass);
            cq.where(cb.equal(root.get(propertyName), value));
            return entityManager.createQuery(cq).getResultList();
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<E> findByProperty(String propertyName, String value, MatchMode matchMode) {
//        convert the value String to lowercase
        value = value.toLowerCase();
        try {
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<E> cq = cb.createQuery(this.entityClass);
            Root<E> root = cq.from(this.entityClass);
            cq.where(cb.like(cb.lower(root.get(propertyName)), matchMode.toMatchString(value)));

            return entityManager.createQuery(cq).getResultList();
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Stream<E> stream() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> q = cb.createQuery(entityClass);
        Root<E> c = q.from(entityClass);
        return entityManager.createQuery(q).getResultStream();
    }

    @SuppressWarnings("unchecked")
    private Class<E> initEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
