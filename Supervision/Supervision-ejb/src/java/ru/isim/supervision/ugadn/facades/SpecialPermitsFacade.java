/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.supervision.ugadn.facades;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ru.isim.supervision.ugadn.entities.SpecialPermits;

/**
 *
 * @author User
 */
@Stateless
public class SpecialPermitsFacade extends AbstractFacade<SpecialPermits> implements SpecialPermitsFacadeLocal {

    @PersistenceContext(unitName = "Supervision-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SpecialPermitsFacade() {
        super(SpecialPermits.class);
    }

    public List<SpecialPermits> findByOrganization(Integer orgID) {
        return em.createNamedQuery("SpecialPermits.findByOrgId").setParameter("orgId", orgID).getResultList();
    }
}
