/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.rsubd.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ru.isim.enterprise.rsubd.entities.Tco;

/**
 *
 * @author User
 */
@Stateless
public class TcoFacade extends AbstractFacade<Tco> implements TcoFacadeLocal {
    @PersistenceContext(unitName = "Enterprise-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TcoFacade() {
        super(Tco.class);
    }
    
}
