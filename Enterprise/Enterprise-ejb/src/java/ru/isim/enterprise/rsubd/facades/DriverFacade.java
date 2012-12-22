/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.rsubd.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ru.isim.enterprise.rsubd.entities.Driver;

/**
 *
 * @author User
 */
@Stateless
public class DriverFacade extends AbstractFacade<Driver> implements DriverFacadeLocal {
    @PersistenceContext(unitName = "Enterprise-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DriverFacade() {
        super(Driver.class);
    }
    
}
