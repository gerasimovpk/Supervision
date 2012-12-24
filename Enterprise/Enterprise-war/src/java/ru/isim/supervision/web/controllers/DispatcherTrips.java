/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.supervision.web.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import ru.isim.enterprise.rsubd.entities.Tco;
import ru.isim.enterprise.rsubd.entities.Waybills;
import ru.isim.enterprise.rsubd.facades.TcoFacadeLocal;
import ru.isim.enterprise.rsubd.facades.WaybillsFacadeLocal;

/**
 *
 * @author User
 */
@Named(value = "dispatcherTrips")
@RequestScoped
public class DispatcherTrips {

    /**
     * Creates a new instance of DispatcherTrips
     */
    public DispatcherTrips() {
    }
    @PostConstruct
    public void construct() {
        waybills =  waybillsFacade.findAll();
    }

    public List<Waybills> getWaybills() {
        return waybills;
    }

    public void setWaybills(List<Waybills> waybills) {
        this.waybills = waybills;
    }

    
    private List<Waybills> waybills = new ArrayList<Waybills>();
    @EJB
    private WaybillsFacadeLocal waybillsFacade;
}
