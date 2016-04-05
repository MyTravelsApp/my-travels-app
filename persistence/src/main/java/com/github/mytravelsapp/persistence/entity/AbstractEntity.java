package com.github.mytravelsapp.persistence.entity;

import java.io.Serializable;

/**
 * Base class for entities
 * @author fjtorres
 */
public abstract class AbstractEntity {

    public abstract Long getId();

    public abstract void setId(Long id);
}
