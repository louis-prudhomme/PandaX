
package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.Publisher;
import javax.ejb.Stateless;

@Stateless
public class PublisherDAO extends AbstractDAO<Publisher>{
    public PublisherDAO(){
        super(Publisher.class);
    }
}
