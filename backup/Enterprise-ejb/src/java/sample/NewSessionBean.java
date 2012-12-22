/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import sample.ejbEE.Logger;
import sample.ejbEE.LoggerFacadeLocal;

/**
 *
 * @author User
 */
@Stateless
public class NewSessionBean implements NewSessionBeanLocal {

    @EJB
    LoggerFacadeLocal logger;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void addRecord() {
        Logger l = new Logger();
        l.setId(BigDecimal.valueOf(2));
        l.setMessage("213");
        logger.create(l);
    }
}
