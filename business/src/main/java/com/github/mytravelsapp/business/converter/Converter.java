package com.github.mytravelsapp.business.converter;

import com.github.mytravelsapp.business.dto.Dto;

import java.util.List;

/**
 * <D> DTO class.
 * <E> Entity class.
 * @author fjtorres
 */
public interface Converter<D extends Dto, E> {
    E convert(D source);

    List<E> convert(List<D> sourceList);

    D convertToDto(E target);

    List<D> convertToDto(List<E> targetList);
}
