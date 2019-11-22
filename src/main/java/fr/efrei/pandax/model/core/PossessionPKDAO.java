
package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.PossessionPK;
import javax.ejb.Stateless;

@Stateless
public class PossessionPKDAO extends AbstractDAO<PossessionPK>{
    public PossessionPKDAO(){
        super(PossessionPK.class);
    }
}
