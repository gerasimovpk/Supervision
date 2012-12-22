/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.ejbEE;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface LoggerFacadeLocal {

    void create(Logger logger);

    void edit(Logger logger);

    void remove(Logger logger);

    Logger find(Object id);

    List<Logger> findAll();

    List<Logger> findRange(int[] range);

    int count();
    
}
