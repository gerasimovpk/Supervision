/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.rsubd;

import java.util.List;
import javax.ejb.Local;
import ru.isim.enterprise.rsubd.entities.Waybills;
import ru.isim.supervision.ugadn.entities.SpecialPermits;

/**
 *
 * @author User
 */
@Local
public interface EnterpriseSFSBLocal {
    List<SpecialPermits> getAvailableSP();
    void createTrip(Waybills trip, int sp)  throws SPTripsCountException;
    int getOrganizationId();
    void setOrganizationId(int orgId);
    
}
