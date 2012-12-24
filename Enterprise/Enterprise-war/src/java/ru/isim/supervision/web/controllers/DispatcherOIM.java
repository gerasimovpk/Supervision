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
import ru.isim.enterprise.rsubd.facades.TcoFacadeLocal;

/**
 *
 * @author User
 */
@Named(value = "dispatcherOIM")
@RequestScoped
public class DispatcherOIM {

    /**
     * Creates a new instance of DispatcherOIM
     */
    public DispatcherOIM() {
    }

    @PostConstruct
    public void construct() {
        tcoList = tcoFacade.findAll();
    }

    public List<Tco> getTcoList() {
        return tcoList;
    }

    public void setTcoList(List<Tco> tcoList) {
        this.tcoList = tcoList;
    }
    private List<Tco> tcoList = new ArrayList<Tco>();
    @EJB
    private TcoFacadeLocal tcoFacade;
}
