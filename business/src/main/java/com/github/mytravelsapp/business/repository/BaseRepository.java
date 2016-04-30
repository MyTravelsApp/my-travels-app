package com.github.mytravelsapp.business.repository;

import com.github.mytravelsapp.business.dto.Dto;
import com.github.mytravelsapp.business.exception.PersistenceException;

import java.io.Serializable;

/**
 * Definition of basic methods to repositories.
 * @author fjtorres
 */
public interface BaseRepository <D extends Dto> {

    /**
     * Persist or update object.
     * @param dto Object to persist.
     * @throws PersistenceException
     */
    void save(D dto) throws PersistenceException;

    /**
     * Retrieve object with given identifier.
     * @param identifier
     * @return Object with given identifier.
     * @throws PersistenceException
     */
    D findById(Long identifier) throws PersistenceException;

    /**
     * Remove object with given identifier.
     * @param identifier
     * @throws PersistenceException
     */
    void remove(Long identifier) throws PersistenceException;
}
