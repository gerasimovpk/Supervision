/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.rsubd;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import ru.isim.enterprise.rsubd.entities.Waybills;
import ru.isim.enterprise.rsubd.facades.WaybillsFacadeLocal;
import ru.isim.supervision.ugadn.entities.SpecialPermits;
import ru.isim.supervision.ugadn.facades.SpecialPermitsFacadeLocal;

/**
 *
 * @author User
 */
@Stateful
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class EnterpriseSFSB implements EnterpriseSFSBLocal {

    @EJB
    private WaybillsFacadeLocal waybillsFacadeLocal;
    
    @EJB
    private SpecialPermitsFacadeLocal spFacadeLocal;
    
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    public void createTrip(Waybills trip, SpecialPermits sp) throws SPTripsCountException {
        if (sp.getTripsCount().toBigInteger() == BigInteger.ZERO) {
            throw new SPTripsCountException();
        }
        waybillsFacadeLocal.create(trip);
        sp.setTripsCount(sp.getTripsCount().subtract(BigDecimal.ONE));
        spFacadeLocal.edit(sp);        
    }
}
