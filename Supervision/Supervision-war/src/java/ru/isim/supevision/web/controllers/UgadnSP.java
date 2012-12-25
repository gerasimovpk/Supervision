/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.supevision.web.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import ru.isim.supervision.ugadn.entities.SpecialPermits;
import ru.isim.supervision.ugadn.facades.CargoTypesFacadeLocal;
import ru.isim.supervision.ugadn.facades.OrganizationsFacadeLocal;
import ru.isim.supervision.ugadn.facades.SpecialPermitsFacadeLocal;

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
        SPList = spfacade.findAll();
        orgList = orgFacade.findAll();
        cargoTypes = cargoTypesFacade.findAll();
    }
    private List<SpecialPermits> SPList = new ArrayList<SpecialPermits>();
    private List<Organizations> orgList = new ArrayList<Organizations>();
    private List<CargoTypes> cargoTypes = new ArrayList<CargoTypes>();
    @EJB
    private SpecialPermitsFacadeLocal spfacade;
    @EJB
    private OrganizationsFacadeLocal orgFacade;
    @EJB
    private CargoTypesFacadeLocal cargoTypesFacade;

    public List<SpecialPermits> getSPList() {
        return SPList;
    }
    private int orgId;
    private int cargoTypeId;
    private Date start;
    private Date finish;
    private String routeDescription;
    private int tripsCount;

    public int getCargoTypeId() {
        return cargoTypeId;
    }

    public void setCargoTypeId(int cargoTypeId) {
        this.cargoTypeId = cargoTypeId;
    }

    public List<CargoTypes> getCargoTypes() {
        return cargoTypes;
    }

    public void setCargoTypes(List<CargoTypes> cargoTypes) {
        this.cargoTypes = cargoTypes;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public List<Organizations> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<Organizations> orgList) {
        this.orgList = orgList;
    }

    public String getRouteDescription() {
        return routeDescription;
    }

    public void setRouteDescription(String routeDescription) {
        this.routeDescription = routeDescription;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public int getTripsCount() {
        return tripsCount;
    }

    public void setTripsCount(int tripsCount) {
        this.tripsCount = tripsCount;
    }

    public void setSPList(List<SpecialPermits> SPList) {
        this.SPList = SPList;
    }

    public void createOrg() {

        System.out.println("start create SP");
        RequestContext.getCurrentInstance().execute("createDialog.hide()");
        SpecialPermits sp = new SpecialPermits();
        sp.setCargoType(cargoTypesFacade.find(cargoTypeId));
        sp.setStartDate(start);
        sp.setFinishDate(finish);
        sp.setOrganiztionId(orgFacade.find(orgId));
        sp.setRouteDescription(routeDescription);
        sp.setTripsCount(BigDecimal.valueOf(tripsCount));

        FacesContext context = FacesContext.getCurrentInstance();

        try {
            spfacade.create(sp);
            SPList = spfacade.findAll();
            context.addMessage(null, new FacesMessage("Сохранение", "СР  успешно сохранено"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Произошла ошибка сервера при сохранении спец.разрешения"));
        }
    }
}
