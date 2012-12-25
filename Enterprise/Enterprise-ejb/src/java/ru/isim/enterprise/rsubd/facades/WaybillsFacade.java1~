/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.rsubd.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ru.isim.enterprise.rsubd.entities.Waybills;

/**
 *
 * @author User
 */
@Stateless
public class WaybillsFacade extends AbstractFacade<Waybills> implements WaybillsFacadeLocal {
    @PersistenceContext(unitName = "Enterprise-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WaybillsFacade() {
        super(Waybills.class);
    }
    
}
