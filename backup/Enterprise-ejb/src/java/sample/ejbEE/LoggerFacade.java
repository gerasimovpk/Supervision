/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.ejbEE;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class LoggerFacade extends AbstractFacade<Logger> implements LoggerFacadeLocal {
    @PersistenceContext(unitName = "Supervision-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoggerFacade() {
        super(Logger.class);
    }
    
}
