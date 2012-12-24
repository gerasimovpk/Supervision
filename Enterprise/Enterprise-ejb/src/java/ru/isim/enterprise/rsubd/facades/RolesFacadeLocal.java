/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.rsubd.facades;

import java.util.List;
import javax.ejb.Local;
import ru.isim.enterprise.rsubd.entities.Roles;

/**
 *
 * @author User
 */
@Local
public interface RolesFacadeLocal {

    void create(Roles roles);

    void edit(Roles roles);

    void remove(Roles roles);

    Roles find(Object id);

    List<Roles> findAll();

    List<Roles> findRange(int[] range);

    public Roles findbyName(String roleName);
    
    int count();
    
}
