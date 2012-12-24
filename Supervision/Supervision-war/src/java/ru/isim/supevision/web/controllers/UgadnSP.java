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
import ru.isim.supervision.ugadn.entities.Organizations;
import ru.isim.supervision.ugadn.facades.OrganizationsFacadeLocal;

/**
 *
 * @author User
 */
@Named(value = "ugadnSP")
@RequestScoped
public class UgadnSP {

    /**
     * Creates a new instance of UgadnSP
     */
    public UgadnSP() {
    }
    
    @PostConstruct
    public void construct() {
        orgList = organizationsFacade.findAll();
    }
    
    private List<Organizations> orgList = new ArrayList<Organizations>();
    @EJB
    private OrganizationsFacadeLocal organizationsFacade;

    public List<Organizations> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<Organizations> orgList) {
        this.orgList = orgList;
    }
    
    private String title;

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void createOrg() {
        
        
        System.out.println("start create org");
        RequestContext.getCurrentInstance().execute("createDialog.hide()");
        Organizations o = new Organizations();
        o.setTitle(title);
        
        
        FacesContext context = FacesContext.getCurrentInstance();
      
        try {
            organizationsFacade.create(o);
        orgList = organizationsFacade.findAll();
            context.addMessage(null, new FacesMessage("Сохранение", "Организация \"" + this.title + "\" успешно сохранена"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Произошла ошибка сервера при сохранении организации"));
        } 
    }
    
}
