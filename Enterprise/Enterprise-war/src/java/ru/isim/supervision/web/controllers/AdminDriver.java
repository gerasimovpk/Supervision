/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.supervision.web.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import ru.isim.enterprise.rsubd.entities.Driver;
import ru.isim.enterprise.rsubd.facades.DriverFacadeLocal;

/**
 *
 * @author User
 */
@Named(value = "adminDriver")
@RequestScoped
public class AdminDriver {

    /**
     * Creates a new instance of AdminTCO
     */
    public AdminDriver() {
    }

    @PostConstruct
    public void construct() {
        driversList = driverFacade.findAll();
    }
    private List<Driver> driversList = new ArrayList<Driver>();
    @EJB
    private DriverFacadeLocal driverFacade;
    private String name;
    private Driver selectedDriver;

    public Driver getSelectedDriver() {
        return selectedDriver;
    }

    public void setSelectedDriver(Driver selectedDriver) {
        this.selectedDriver = selectedDriver;
    }

    public List<Driver> getDriversList() {
        return driversList;
    }

    public void setDriversList(List<Driver> driversList) {
        this.driversList = driversList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void createDriver() {
        System.out.println("start create driver");
        RequestContext.getCurrentInstance().execute("createDialog.hide()");
        Driver dr = new Driver();
        dr.setName(name);

        FacesContext context = FacesContext.getCurrentInstance();

        try {
            driverFacade.create(dr);
            context.addMessage(null, new FacesMessage("Сохранение", "Водитель \"" + this.name + "\" успешно сохранен"));
            driversList = driverFacade.findAll();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Произошла ошибка сервера при сохранении водителя"));
        }
    }

    public void removeDriver(Driver driver) {
        System.out.println("startremove");
        driverFacade.remove(driver);
        driversList = driverFacade.findAll();
    }
}
