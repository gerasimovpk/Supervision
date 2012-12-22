/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.rsubd.facades;

import java.util.List;
import javax.ejb.Remote;
import ru.isim.enterprise.rsubd.entities.Tco;

/**
 *
 * @author User
 */
@Remote
public interface TcoFacadeRemote {

    void create(Tco tco);

    void edit(Tco tco);

    void remove(Tco tco);

    Tco find(Object id);

    List<Tco> findAll();

    List<Tco> findRange(int[] range);

    int count();
    
}
