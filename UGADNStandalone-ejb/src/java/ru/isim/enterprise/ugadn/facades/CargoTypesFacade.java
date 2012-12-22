/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.ugadn.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ru.isim.enterprise.ugadn.entities.CargoTypes;

/**
 *
 * @author User
 */
@Stateless
public class CargoTypesFacade extends AbstractFacade<CargoTypes> implements CargoTypesFacadeLocal {
    @PersistenceContext(unitName = "UGADNStandalone-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CargoTypesFacade() {
        super(CargoTypes.class);
    }
    
}
