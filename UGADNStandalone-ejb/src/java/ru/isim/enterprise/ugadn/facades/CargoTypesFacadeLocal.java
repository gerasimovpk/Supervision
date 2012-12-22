/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.ugadn.facades;

import java.util.List;
import javax.ejb.Local;
import ru.isim.enterprise.ugadn.entities.CargoTypes;

/**
 *
 * @author User
 */
@Local
public interface CargoTypesFacadeLocal {

    void create(CargoTypes cargoTypes);

    void edit(CargoTypes cargoTypes);

    void remove(CargoTypes cargoTypes);

    CargoTypes find(Object id);

    List<CargoTypes> findAll();

    List<CargoTypes> findRange(int[] range);

    int count();
    
}
