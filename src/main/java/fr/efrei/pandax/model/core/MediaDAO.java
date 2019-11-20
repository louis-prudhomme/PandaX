package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.Media;
import javax.ejb.Stateless;

@Stateless
public class MediaDAO extends AbstractDAO<Media> {   
    public MediaDAO(){
        super(Media.class);
    }
}
