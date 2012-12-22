/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.ugadn.facades;

import java.util.List;
import javax.ejb.Local;
import ru.isim.enterprise.ugadn.entities.SpecialPermits;

/**
 *
 * @author User
 */
@Local
public interface SpecialPermitsFacadeLocal {

    void create(SpecialPermits specialPermits);

    void edit(SpecialPermits specialPermits);

    void remove(SpecialPermits specialPermits);

    SpecialPermits find(Object id);

    List<SpecialPermits> findAll();

    List<SpecialPermits> findRange(int[] range);

    int count();
    
}
