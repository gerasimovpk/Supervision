/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.supervision.web.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import ru.isim.enterprise.rsubd.EnterpriseSFSBLocal;
import ru.isim.enterprise.rsubd.entities.Waybills;
import ru.isim.enterprise.rsubd.facades.DriverFacade;
import ru.isim.supervision.ugadn.entities.SpecialPermits;

/**
 *
 * @author User
 */
@Named(value = "dispatcherSP")
@RequestScoped
public class DispatcherSP {

    /**
     * Creates a new instance of DispatcherSP
     */
    public DispatcherSP() {
    }

    @PostConstruct
    public void construct() {
        splist = enterprise.getAvailableSP();
    }
    private List<SpecialPermits> splist = new ArrayList<SpecialPermits>();
    @EJB
    private EnterpriseSFSBLocal enterprise;

    public List<SpecialPermits> getSplist() {
        return splist;
    }

    public void setSplist(List<SpecialPermits> splist) {
        this.splist = splist;
    }
    
    
}
