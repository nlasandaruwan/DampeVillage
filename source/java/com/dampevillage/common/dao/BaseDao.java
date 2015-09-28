package com.dampevillage.common.dao;

import java.io.Serializable;
import java.util.List;

import com.dampevillage.common.entity.BaseEntity;

public interface BaseDao <T extends BaseEntity, ID extends Serializable>{

	/**
     * Method to insert an domain object which is extended by an Persistent
     * class.
     *
     * @param entity
     *            contains Persistent or Persistent sub class.
     * @param id
     *            -
     * @throws CitiOlrException
     *             - CitiCommonsException to be thrown.
     *
     */
    void createOrUpdate(T entity, ID id) throws Exception;

    /**
     * Method to flush persistance context.
     *
     */
    void flush();

    /**
     * To get the list of all available domain specific objects.
     *
     * @param start
     *            - start value.
     * @param max
     *            - maximum no of reports.
     * @return List of domain specific objects.
     */
   
    List<T> getAll(final int start, final Integer max);

    /**
     * To get a particular domain specific object by id.
     *
     * @param id
     *            - id of loading object.
     * @return a domain specific objects.
     */
   
    T getEntityById(ID id);

    /**
     * To update a particular domain specific object already persisted.
     *
     * @param entity
     *            - object need to persist.
     * @return a updated domain specific objects.
     */
    T merge(T entity);

    /**
     * To remove a particular domain specific object already persisted.
     *
     * @param entity
     *            - object need to persist.
     * @param id
     *            -
     * @throws CitiOlrException
     *             - CitiCommonsException to be thrown.
     */
    void remove(T entity, ID id) throws Exception;
    
}
