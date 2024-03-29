/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.rsubd.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ru.isim.enterprise.rsubd.entities.GlobalOptions;

/**
 *
 * @author User
 */
@Stateless
public class GlobalOptionsFacade extends AbstractFacade<GlobalOptions> implements GlobalOptionsFacadeLocal {
    @PersistenceContext(unitName = "Enterprise-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GlobalOptionsFacade() {
        super(GlobalOptions.class);
    }
    
    public int getOrganizationId() {
        return Integer.parseInt(((GlobalOptions)em.createNamedQuery("GlobalOptions.findByOptionName").setParameter("optionName", "ORG_ID").getResultList().get(0)).getOptionValue());
    }
}
