/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.rsubd.facades;

import java.util.List;
import javax.ejb.Local;
import ru.isim.enterprise.rsubd.entities.Waybills;

/**
 *
 * @author User
 */
@Local
public interface WaybillsFacadeLocal {

    void create(Waybills waybills);

    void edit(Waybills waybills);

    void remove(Waybills waybills);

    Waybills find(Object id);

    List<Waybills> findAll();

    List<Waybills> findRange(int[] range);

    int count();
    
}
