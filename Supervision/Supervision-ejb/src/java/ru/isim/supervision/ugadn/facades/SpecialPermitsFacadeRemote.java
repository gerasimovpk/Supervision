/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.supervision.ugadn.facades;

import java.util.List;
import javax.ejb.Remote;
import ru.isim.supervision.ugadn.entities.SpecialPermits;

/**
 *
 * @author User
 */
@Remote
public interface SpecialPermitsFacadeRemote {

    void create(SpecialPermits specialPermits);

    void edit(SpecialPermits specialPermits);

    void remove(SpecialPermits specialPermits);

    SpecialPermits find(Object id);

    List<SpecialPermits> findByOrganization(Integer orgID);

    List<SpecialPermits> findAll();

    List<SpecialPermits> findRange(int[] range);

    int count();
}
