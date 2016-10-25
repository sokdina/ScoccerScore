package cz.fi.muni.pa165.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jaromir Sys
 */
public class BaseDao {
    @PersistenceContext
    protected EntityManager entityManager;
}
