/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.CommentsPK;
import javax.ejb.Stateless;


@Stateless
public class CommentsPKDAO extends AbstractDAO<CommentsPK>{   
    public CommentsPKDAO(){
        super(CommentsPK.class);
    }
}
