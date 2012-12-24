/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.supervision.ugadn.facades;

import java.util.List;
import javax.ejb.Local;
import ru.isim.supervision.ugadn.entities.Users;

/**
 *
 * @author User
 */
@Local
public interface UsersFacadeLocal {

    void create(Users users);

    void edit(Users users);

    void remove(Users users);

    Users find(Object id);

    List<Users> findAll();

    List<Users> findRange(int[] range);

    int count();
    
}
