/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.rsubd;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import ru.isim.enterprise.rsubd.entities.GlobalOptions;
import ru.isim.enterprise.rsubd.entities.Waybills;
import ru.isim.enterprise.rsubd.facades.GlobalOptionsFacadeLocal;
import ru.isim.enterprise.rsubd.facades.WaybillsFacadeLocal;
import ru.isim.supervision.ugadn.entities.SpecialPermits;
import ru.isim.supervision.ugadn.facades.SpecialPermitsFacadeLocal;
import ru.isim.supervision.ugadn.facades.SpecialPermitsFacadeRemote;

/**
 *
 * @author User
 */
@Stateful
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class EnterpriseSFSB implements EnterpriseSFSBLocal {

    private int OrganizationId;
    @EJB
    private WaybillsFacadeLocal waybillsFacadeLocal;
    @EJB
    private SpecialPermitsFacadeLocal spFacadeLocal;
    @EJB
    private GlobalOptionsFacadeLocal optfacade;

    @PostConstruct
    public void setOrgId() {
        OrganizationId = optfacade.getOrganizationId();
    }

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public void createTrip(Waybills trip, int specialPermitId) throws SPTripsCountException {
        SpecialPermits sp = spFacadeLocal.find(specialPermitId);
        
        if (sp.getTripsCount().toBigInteger() == BigInteger.ZERO) {
            throw new SPTripsCountException();
        }
        waybillsFacadeLocal.create(trip);
        sp.setTripsCount(sp.getTripsCount().subtract(BigDecimal.ONE));
        spFacadeLocal.edit(sp);
    }

    public List<SpecialPermits> getAvailableSP() {
        return spFacadeLocal.findByOrganization(OrganizationId);
    }

    public int getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(int OrganizationId) {
        this.OrganizationId = OrganizationId;
    }
}
