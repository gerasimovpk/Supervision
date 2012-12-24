/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.supervision.ugadn.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ru.isim.supervision.ugadn.entities.Organizations;

/**
 *
 * @author User
 */
@Stateless
public class OrganizationsFacade extends AbstractFacade<Organizations> implements OrganizationsFacadeLocal {
    @PersistenceContext(unitName = "Supervision-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrganizationsFacade() {
        super(Organizations.class);
    }
    
}
