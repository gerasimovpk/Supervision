/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.supevision.web.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import ru.isim.supervision.ugadn.entities.CargoTypes;
import ru.isim.supervision.ugadn.entities.Organizations;
import ru.isim.supervision.ugadn.facades.CargoTypesFacadeLocal;
import ru.isim.supervision.ugadn.facades.OrganizationsFacadeLocal;

/**
 *
 * @author User
 */
@Named(value = "ugadnCargoType")
@RequestScoped
public class UgadnCargoType {

    /**
     * Creates a new instance of UgadnCargoType
     */
    public UgadnCargoType() {
    }
    
    @PostConstruct
    public void construct() {
        cargoTypes = cargoTypesFacade.findAll();
    }
    
    private List<CargoTypes> cargoTypes = new ArrayList<CargoTypes>();
    @EJB
    private CargoTypesFacadeLocal cargoTypesFacade;

    public List<CargoTypes> getCargoTypes() {
        return cargoTypes;
    }

    public void setCargoTypes(List<CargoTypes> cargoTypes) {
        this.cargoTypes = cargoTypes;
    }

    private String title;

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void createCargoType() {
        
        
        System.out.println("start create cargo type");
        RequestContext.getCurrentInstance().execute("createDialog.hide()");
        CargoTypes ct = new CargoTypes();
        ct.setTitle(title);
        
        FacesContext context = FacesContext.getCurrentInstance();
      
        try {
            cargoTypesFacade.create(ct);
            cargoTypes = cargoTypesFacade.findAll();
            context.addMessage(null, new FacesMessage("Сохранение", "Тип груза \"" + this.title + "\" успешно сохранен"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Произошла ошибка сервера при сохранении типа груза"));
        } 
    }
}
