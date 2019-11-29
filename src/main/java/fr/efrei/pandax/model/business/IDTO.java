package fr.efrei.pandax.model.business;

/**
 * This interface enable us to mark Data Transfer Objects as such
 * and forces them to implement a way to get their database identifier
 */
public interface IDTO {
    Integer getId();
}
