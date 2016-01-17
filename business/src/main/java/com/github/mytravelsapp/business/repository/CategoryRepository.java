package com.github.mytravelsapp.business.repository;

import com.github.mytravelsapp.business.dto.CategoryDto;
import com.github.mytravelsapp.business.exception.PersistenceException;

import java.util.List;

/**
 * @author stefani
 */
public interface CategoryRepository {

    void save(CategoryDto dto) throws PersistenceException;

    List<CategoryDto> find(String textFilter) throws PersistenceException;

    void remove(Long identifier) throws PersistenceException;
}
