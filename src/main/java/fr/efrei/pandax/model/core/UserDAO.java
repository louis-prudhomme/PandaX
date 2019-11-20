package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.User;
import javax.ejb.Stateless;

@Stateless
public class UserDAO extends AbstractDAO<User>{        
    public UserDAO(){
        super(User.class);
    }
}
