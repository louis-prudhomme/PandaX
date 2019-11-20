package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.MediaType;
import javax.ejb.Stateless;

@Stateless
public class MediaTypeDAO extends AbstractDAO<MediaType> {
    public MediaTypeDAO(){
        super(MediaType.class);
    }
}