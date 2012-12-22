/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.supervision.web.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.PostLoad;
import org.primefaces.context.RequestContext;
import ru.isim.enterprise.rsubd.entities.Tco;
import ru.isim.enterprise.rsubd.facades.TcoFacadeLocal;
import sun.java2d.SunGraphicsEnvironment;

/**
 *
 * @author User
 */
@Named(value = "adminTCO")
@RequestScoped
public class AdminTCO {

    /**
     * Creates a new instance of AdminTCO
     */
    public AdminTCO() {
    }

    @PostConstruct
    public void construct() {
        tcoList = tcoFacade.findAll();
    }
    private List<Tco> tcoList = new ArrayList<Tco>();
    @EJB
    private TcoFacadeLocal tcoFacade;
    private String atId;
    private String stateNumber;
    private String mark;
    private String model;
    private Integer weight;
    private Tco selectedTco;

    public Tco getSelectedTco() {
        return selectedTco;
    }

    public void setSelectedTco(Tco selectedTco) {
        this.selectedTco = selectedTco;
    }

    public List<Tco> getTcoList() {
        return tcoList;
    }

    public void setTcoList(List<Tco> tcoList) {
        this.tcoList = tcoList;
    }

    public String getAtId() {
        return atId;
    }

    public void setAtId(String atId) {
        this.atId = atId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void createTco() {
        System.out.println("start create tco");
        RequestContext.getCurrentInstance().execute("createDialog.hide()");
        Tco tco = new Tco();
        FacesContext context = FacesContext.getCurrentInstance();
        tco.setAtId(this.atId);

        tco.setStateNumber(this.stateNumber);

        tco.setMark(this.mark);
        tco.setModel(this.model);
        tco.setWeight(this.weight);
        try {
            tcoFacade.create(tco);
            context.addMessage(null, new FacesMessage("Сохранение", "ОТК \"" + this.stateNumber + "\" успешно сохранено"));
            tcoList = tcoFacade.findAll();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Произошла ошибка сервера при сохранении ОТК"));
        } 
    }

    public void removeTco(Tco tco) {
        System.out.println("startremove");
        tcoFacade.remove(tco);
        tcoList = tcoFacade.findAll();
    }
}
