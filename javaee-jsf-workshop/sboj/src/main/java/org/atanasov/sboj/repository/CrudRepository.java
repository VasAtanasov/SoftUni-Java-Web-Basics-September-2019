package org.atanasov.sboj.repository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Generic repository interface for common data access functionality
 *
 * @author Vasil Atanasov
 */
public interface CrudRepository<E, PK extends Serializable> {

    /**
     * <p>
     * The ordering of a collection of objects.
     * </p>
     *
     * <ul>
     * <li><b>ASC</b>: ascending order.
     * <li><b>DESC</b>: descending order.
     * </ul>
     */
    enum Order {

        ASC, DESC;

        public boolean isAscOrder() {
            return ASC.equals(this);
        }
    }

    enum MatchMode {
        EXACT {
            public String toMatchString(String pattern) {
                return pattern;
            }
        },
        START {
            public String toMatchString(String pattern) {
                return pattern + '%';
            }
        },
        END {
            public String toMatchString(String pattern) {
                return '%' + pattern;
            }
        },
        ANYWHERE {
            public String toMatchString(String pattern) {
                return '%' + pattern + '%';
            }
        };

        MatchMode() {
        }

        public abstract String toMatchString(String var1);
    }

    /**
     * Get the Class of the entity.
     *
     * @return the class
     */
    Class<E> getEntityClass();

    /**
     * Setting for EntityManager DI
     *
     * @param entityManager An instance of an EntityManager
     */
    void setEntityManager(EntityManager entityManager);

    /**
     * Getter for EntityManager
     *
     * @return {@link EntityManager}
     */
    EntityManager getEntityManager();

    /**
     * Count all entities.
     *
     * @return the number of entities
     */
    int count();

    /**
     * Find an entity by its primary key
     *
     * @param id the primary key
     * @return the entity
     */
    E find(final PK id);

    /**
     * Find an entity by its primary key
     *
     * @param id the primary key
     * @return the entity as optional
     */
    Optional<E> findOptional(PK id);

    /**
     * Save an entity. This is an "INSERT or UPDATE" operation based on
     * the existence of an entity in the current persistence context. This
     * is an approximation of the Hibernate "Session.saveOrUpdate()" method.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    E save(final E entity);

    /**
     * Marges objects with the same identifier within a session into a newly
     * created object.
     *
     * @param entity the entity to merge
     * @return a newly created instance merged.
     */
    E merge(final E entity);

    /**
     * Delete an entity from the persistence store
     *
     * @param entity the entity to delete˝
     */
    void remove(final E entity);

    /**
     * Delete an entity from the persistence store by Primary Key ID
     *
     * @param id the entity identifier
     */
    void remove(final PK id);

    /**
     * Load all entities.
     *
     * @return the list of entities
     */
    List<E> findAll();

    /**
     * Finds all objects of a class by the specified order.
     *
     * @param order           the order: ASC or DESC.
     * @param propertiesOrder the properties on which to apply the ordering.
     * @return the list of entities
     */
    List<E> findAll(Order order, String... propertiesOrder);

    /**
     * Find using a named query.
     * <p>
     * Note that Named Queries are configured in the Entities and look
     * like this:
     *
     * <pre>
     *
     * {@literal @}Entity
     * {@literal @}NamedQuery(name="findSalaryForNameAndDepartment",
     *   query="SELECT e.salary " +
     *         "FROM Employee e " +
     *         "WHERE e.department.name = :deptName AND " +
     *         "      e.name = :empName")
     *  public class Employee {
     *  ...
     * </pre>
     *
     * @param queryName the name of the query
     * @param params    the query parameters
     * @return the list of entities
     */
    List<E> findByNamedQuery(final String queryName, Object... params);

    /**
     * Find using a named query.
     *
     * @param queryName the name of the query
     * @param params    the query parameters
     * @return the list of entities
     */
    List<E> findByNamedQueryAndNamedParams(final String queryName, final Map<String, Object> params);

    /**
     * Finds an entity by one of its properties.
     *
     * @param propertyName the property name.
     * @param value        the value by which to find.
     * @return the list of entities
     */
    List<E> findByProperty(String propertyName, Object value);

    /**
     * Finds entities by a String property specifying a MatchMode. This search
     * is case insensitive.
     *
     * @param propertyName the property name.
     * @param value        the value to check against.
     * @param matchMode    the match mode: EXACT, START, END, ANYWHERE.
     * @return the list of entities
     */
    List<E> findByProperty(String propertyName, String value, MatchMode matchMode);

    /**
     * Load all entities as stream.
     *
     * @return the stream of entities
     */
    Stream<E> stream();
}