/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.supervision.ugadn.facades;

import java.util.List;
import javax.ejb.Local;
import ru.isim.supervision.ugadn.entities.Organizations;

/**
 *
 * @author User
 */
@Local
public interface OrganizationsFacadeLocal {

    void create(Organizations organizations);

    void edit(Organizations organizations);

    void remove(Organizations organizations);

    Organizations find(Object id);

    List<Organizations> findAll();

    List<Organizations> findRange(int[] range);

    int count();
    
}
